package net.virtalab.mvctools.logger;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logger
 */
public class RequestLogger extends HandlerInterceptorAdapter {
    private static final String INFO = "logInfo";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LogInfo info = new LogInfo();

        long ts = System.currentTimeMillis();
        info.setTimestamp(ts);

        info.setRequestIp(request.getRemoteAddr());
        info.setRequestUri(request.getRequestURI());
        info.setRequestBody(ServletTools.getRequestBody(request));

        long start = System.currentTimeMillis();
        info.setStartTime(start);

        request.setAttribute(INFO,info);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LogInfo info = (LogInfo) request.getAttribute(INFO);
        info.setResponseStatus(response.getStatus());

        long endTime = System.currentTimeMillis();
        info.setEndTime(endTime);

        System.out.println(info.toLog());
    }

    public static void setResponseBody(String body, HttpServletRequest request){
         LogInfo info = (LogInfo) request.getAttribute(INFO);
         info.setResponseBody(body);
    }
}