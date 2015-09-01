package net.virtalab.mvctools.logger.weblogger;

import net.virtalab.mvctools.logger.RequestLogInfo;
import net.virtalab.mvctools.logger.RequestLogger;

/**
 * Log to HTML
 */
@SuppressWarnings("UnusedDeclaration")
public class HtmlLogger implements RequestLogger {
    @Override
    public void log(RequestLogInfo info) {
        StringBuilder sb = new StringBuilder();
        String t = "<BR>";

        sb.append("Got request at ").append(info.getTimestamp()).append(t);
        sb.append(t);
        sb.append("==Request data==").append(t);
        sb.append("Client IP: ").append(info.getRequestIp()).append(t);
        sb.append("Request URI: ").append(info.getRequestUri()).append(t);
        sb.append("Request body: ").append(info.getRequestBody()).append(t);
        sb.append("Start serving at ").append(info.getStartTime()).append(t);
        sb.append(t);
        sb.append("==Response data==").append(t);
        sb.append("Response status: ").append(info.getResponseStatus()).append(t);
        sb.append("Response body: ").append(info.getResponseBody()).append(t);
        sb.append("Served in ").append(info.getServedAt()).append(" ms").append(t);

        if(info.getStackTrace() != null) {
            sb.append("Exception stack trace ").append(info.getStackTrace()).append(t);
        }
    }
}
