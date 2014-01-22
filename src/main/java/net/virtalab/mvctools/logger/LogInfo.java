package net.virtalab.mvctools.logger;

/**
 * Information that will be logged .
 */
public class LogInfo {
    private long timestamp;
    private long startTime;
    private long servedAt;

    private String requestIp;
    private String requestUri;
    private String requestBody;

    private String responseBody;
    private int responseStatus;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }


    public long getServedAt() {
        return servedAt;
    }

    public void setEndTime(long endTime) {
        this.servedAt = endTime - this.startTime;
    }

    public String toLog() {
        StringBuilder sb = new StringBuilder();
        String t = System.getProperty("line.separator");
        sb.append("Got request at ").append(timestamp).append(t);
        sb.append(t);
        sb.append("==Request data==").append(t);
        sb.append("Client IP: ").append(requestIp).append(t);
        sb.append("Request URI: ").append(requestUri).append(t);
        sb.append("Request body: ").append(requestBody).append(t);
        sb.append("Start serving at ").append(startTime).append(t);
        sb.append(t);
        sb.append("==Response data==").append(t);
        sb.append("Response status: ").append(responseStatus).append(t);
        sb.append("Response body: ").append(responseBody).append(t);
        sb.append("Served in ").append(servedAt).append(" ms").append(t);

        return sb.toString();
    }
}
