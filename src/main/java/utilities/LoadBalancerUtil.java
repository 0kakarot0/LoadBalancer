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

    private static final String HTTP_METHOD_STRING = "HTTP/1.1";
    private static final String USER_AGENT_STRING = "/7.85.0";
    private static final String ACCEPT_STRING = "*/*";

    public static LinkedHashMap<String, String> startTheLoadBalancer(String initialCommand) throws UnknownHostException {
        fillTheModel(initialCommand);
        contructInformation.put("Received request from ", loadBalancerInformation.getRequestIp());
        contructInformation.put("GET /", loadBalancerInformation.getHttpMethod());
        contructInformation.put("Host: ", loadBalancerInformation.getHostName());
        contructInformation.put("User-Agent: ", loadBalancerInformation.getUserAgent());
        contructInformation.put("Accept: ", loadBalancerInformation.getAccept());
        return contructInformation;
    }

    private static void fillTheModel(String initialCommand) throws UnknownHostException {
        loadBalancerInformation = new LoadBalancerInformation(
                InetAddress.getByName("localhost").getHostAddress(),
                HTTP_METHOD_STRING,
                InetAddress.getByName("localhost").getHostName(),
                getUserAgentString(initialCommand),
                ACCEPT_STRING
        );
    }

    private static String getUserAgentString(String initialCommand) {
        return initialCommand + USER_AGENT_STRING;

    }


}
