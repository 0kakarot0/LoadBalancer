package utilities;

class Requests_Concurrency extends Thread {
    private Thread thread;
    private String threadName;
    private int totalRequest;

    Requests_Concurrency(String name, int totalRequest) {
        this.threadName = name;
        this.totalRequest = totalRequest;
    }

    @Override
    public void run() {
        try {
            System.out.println("Response from server: HTTP/1.1 200 OK\n" + "\n" + "Hello From Backend Server");
        } catch (Exception e) {
            System.out.println("Invalid Request to server");
        }
    }
}


public class RequestToServer {
    public static void sendRequestToBackEndServer(String threadName, int totalRequest) {
        Requests_Concurrency requestsConcurrency = new Requests_Concurrency(threadName, totalRequest);
        requestsConcurrency.start();
    }
}
