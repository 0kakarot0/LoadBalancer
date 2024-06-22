package utilities;

import java.io.IOException;

class Requests_Concurrency extends Thread {
    private Thread thread;
    private String threadName;
    private int totalRequest;
    private String receivedRequestOnServer;
    private StringBuilder stringBuilder;

    Requests_Concurrency(String name, int totalRequest, String receivedRequestOnServer) {
        this.threadName = name;
        this.totalRequest = totalRequest;
        this.receivedRequestOnServer = receivedRequestOnServer;
    }


    @Override
    public void run() {
        try {
//            for (int i = 0; i < totalRequest; i++) {
            System.out.println(returnProperFormattedResponse());
//                System.out.println("Response from server: HTTP/1.1 200 OK\n" + "Hello From Backend Server"+ "\n" );
//            }
        } catch (Exception e) {
            System.out.println("Server Not Alive " + threadName);
        }
    }

    private String returnProperFormattedResponse() {
        try {
            stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            stringBuilder.append(receivedRequestOnServer).append("\n");
            stringBuilder.append("************************* RESPONSE START *************************").append("\n");
            stringBuilder.append(MyHTTPRequestToServer.returnResponseFromServer(threadName)).append("\n");
            stringBuilder.append("************************* RESPONSE ENDS *************************").append("\n");
            Thread.sleep(500);
            return stringBuilder.toString();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


public class RequestToServer {
    public static void sendRequestToBackEndServer(String threadName, int totalRequest, String receivedRequestOnServer) {
        Requests_Concurrency requestsConcurrency = new Requests_Concurrency(threadName, totalRequest, receivedRequestOnServer);
        requestsConcurrency.start();
    }
}
