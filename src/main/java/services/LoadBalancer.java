package services;

import utilities.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoadBalancer {
    private static int currentServerIndex = 0;
    private static List<String> servers = new ArrayList<>();

    public static LinkedHashMap<String, String> startUpLoadBalancerServer(String commandFromTest) throws UnknownHostException {
        String initialCommand = commandFromTest.substring(0, commandFromTest.indexOf(" "));
        return LoadBalancerUtil.startTheLoadBalancer(initialCommand);
    }

    private static void startTheLoadBalancer(String currentCommand) {
        boolean isStarting = true;
        try {
            for (Map.Entry<String, String> current : LoadBalancer.startUpLoadBalancerServer(currentCommand).entrySet()) {
                if (isStarting) {
                    System.out.println("\n");
                    System.out.println("./lb");
                    isStarting = false;
                }
                System.out.println(current.getKey() + current.getValue());
            }
            System.out.println(" ");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        List<String> commandList = new ArrayList<>();

        List<String> serverListFromFile = ServerUtils.getListOfServer();

        addTheServerAndStartLoadBalancer(serverListFromFile, commandList);

        startHealthMonitoring();
        sendRequestToBankEndServers(commandList);
    }

    private static void sendRequestToBankEndServers(List<String> commandList) {
        for (String command : commandList) {
            String server = getNextServer();
            addServerAgainIfAlive(server);
            String receivedRequestOnServer = "Routing " + command + " to server: " + server;
            RequestToServer.sendRequestToBackEndServer(server, commandList.size(), receivedRequestOnServer);
            removeFromServerIfNotAlive(server);

        }
    }

    private static void addTheServerAndStartLoadBalancer(List<String> serverListFromFile, List<String> commandList) {
        for (int i = 0; i < serverListFromFile.size(); i++) {
            if (i == 0){
                startTheLoadBalancer("curl "+serverListFromFile.get(i));
            }
            String currentURL = serverListFromFile.get(i);
            if (MyHTTPRequestToServer.isServerAlive(currentURL)) {
                servers.add(currentURL);
                commandList.add("Request " + (i + 1));
            }

        }
    }


    private static String getNextServer() {
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available to handle the request.");
        }
        String server = servers.get(currentServerIndex);
        currentServerIndex = (currentServerIndex + 1) % servers.size();
        return server;
    }

    private static void addServerAgainIfAlive(String url) {
        if (!servers.contains(url) && MyHTTPRequestToServer.isServerAlive(url)) {
            servers.add(url);
        }
    }

    private static void removeFromServerIfNotAlive(String url) {
        if (MyHTTPRequestToServer.isServerAlive(url)) {
            servers.remove(url);
        }
    }

    private static void startHealthMonitoring(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            for (String server : servers){
                removeFromServerIfNotAlive(server);
                addServerAgainIfAlive(server);
            }
        },0,10, TimeUnit.MILLISECONDS);
    }

    private static String inCaseOfSingleURLCommand() {
        // ========> In case of user input command  <============
        return UserInput.getUserInput();
    }

}
