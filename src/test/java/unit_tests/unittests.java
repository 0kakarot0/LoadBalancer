package unit_tests;

import org.testng.annotations.Test;
import services.LoadBalancer;
import utilities.ReadPropertyFile;


import java.net.UnknownHostException;
import java.util.Map;

public class unittests {

    @Test(testName = "Start Load Balancer", description = "Verify User can see the message upon sending a request to load balancer")
    private void sendRequestToLoadBalancer() throws UnknownHostException {
        ReadPropertyFile readPropertyFile = new ReadPropertyFile();
        String userCommand = readPropertyFile.getUserCommand();
        boolean isStarting = true;
        for (Map.Entry<String, String> current : LoadBalancer.startUpLoadBalancerServer(userCommand).entrySet()) {
            if (isStarting) {
                System.out.println("./lb");
                isStarting = false;
            }
            System.out.println(current.getKey() + current.getValue());
        }
    }



}
