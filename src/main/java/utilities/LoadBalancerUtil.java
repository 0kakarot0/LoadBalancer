package utilities;

import model.LoadBalancerInformation;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoadBalancerUtil {
    private static LoadBalancerInformation loadBalancerInformation;
    private static LinkedHashMap<String, String> contructInformation = new LinkedHashMap<>();

    public static LinkedHashMap<String, String> startTheLoadBalancer(String initailCommand) throws UnknownHostException {
        fillTheModel(initailCommand);
        contructInformation.put("Received request from ", loadBalancerInformation.getRequestIp());
        contructInformation.put("GET /", loadBalancerInformation.getHttpMethod());
        contructInformation.put("Host: ", loadBalancerInformation.getHostName());
        contructInformation.put("User-Agent: ", loadBalancerInformation.getUserAgent());
        contructInformation.put("Accept: ", loadBalancerInformation.getAccept());
        return contructInformation;
    }

    private static void fillTheModel(String initailCommand) throws UnknownHostException {
        loadBalancerInformation = new LoadBalancerInformation(
                InetAddress.getByName("localhost").getHostAddress(),
                "HTTP/1.1",
                InetAddress.getByName("localhost").getHostName(),
                initailCommand + "/7.85.0",
                "*/*"
        );
    }


}
