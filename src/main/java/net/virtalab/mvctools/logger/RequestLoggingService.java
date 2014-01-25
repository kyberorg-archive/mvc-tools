package net.virtalab.mvctools.logger;

import net.virtalab.mvctools.internal.ServletTools;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * RequestLogger
 */
@Service
public class RequestLoggingService extends HandlerInterceptorAdapter {
    public static final String INFO = "logInfo";
    public static final String EXCEPTION = "exception";
    public static final String RESPONSE_BODY = "respBody";

    @Autowired
    @Qualifier("requestLogger")
    private RequestLogger requestLogger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestLogInfo info = new RequestLogInfo();

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
        if(!ServletTools.isAttributePresent(INFO,request)){
            System.err.println("RequestLogger info object is not present. Won't logging");
            return;
        }
        RequestLogInfo info = (RequestLogInfo) request.getAttribute(INFO);

        info.setResponseStatus(response.getStatus());

        if(ServletTools.isAttributePresent(RESPONSE_BODY,request)){
            String responseBody = (String) request.getAttribute(RESPONSE_BODY);
            info.setResponseBody(responseBody);
        }
        if(ServletTools.isAttributePresent(EXCEPTION,request)){
            Exception e = (Exception) request.getAttribute(EXCEPTION);
            String trace = ExceptionUtils.getFullStackTrace(e);
            info.setStackTrace(trace);
        }

        long endTime = System.currentTimeMillis();
        info.setEndTime(endTime);

        if(this.requestLogger != null){
            this.requestLogger.log(info);
        }
    }
}