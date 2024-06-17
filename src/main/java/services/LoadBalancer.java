package services;

import utilities.LoadBalancerUtil;
import utilities.RequestToServer;
import utilities.UserInput;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoadBalancer {


    public static LinkedHashMap<String, String> startUpLoadBalancerServer(String commandFromTest) throws UnknownHostException {
        String initialCommand;
        if (!commandFromTest.isEmpty()) {
            initialCommand = commandFromTest.substring(0, commandFromTest.indexOf(" "));
        } else {
            String userCommand = UserInput.getUserInput();
            initialCommand = userCommand.substring(0, userCommand.indexOf(" "));
        }
        return LoadBalancerUtil.startTheLoadBalancer(initialCommand);
    }

    public static void main(String[] args) throws UnknownHostException {
        boolean isStarting = true;
        for (Map.Entry<String, String> current : LoadBalancer.startUpLoadBalancerServer("").entrySet()) {
            if (isStarting) {
                System.out.println("./lb");
                isStarting = false;
            }
            System.out.println(current.getKey() + current.getValue());
        }
        System.out.println(" ");
        RequestToServer.sendRequestToBackEndServer(startUpLoadBalancerServer().get("Received request from "), 3);
    }


}
