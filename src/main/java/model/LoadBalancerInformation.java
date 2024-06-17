package model;

public class LoadBalancerInformation {

    private String requestIp;
    private String httpMethod;
    private String hostName;
    private String userAgent;
    private String accept;

    public LoadBalancerInformation(String requestIp, String httpMethod, String hostName, String userAgent, String accept) {
        this.requestIp = requestIp;
        this.httpMethod = httpMethod;
        this.hostName = hostName;
        this.userAgent = userAgent;
        this.accept = accept;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

}
