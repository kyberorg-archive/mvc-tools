package net.virtalab.mvctools.internal;

import net.virtalab.mvctools.logger.RequestLogInfo;
import net.virtalab.mvctools.logger.RequestLoggingService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Static method what make servlet live easy
 */
public class ServletTools {
    /**
     * Reads body from Request object
     * @param request Request object
     * @return Body as String
     */
    public static String getRequestBody(HttpServletRequest request) {

        if(request==null){ throw new IllegalArgumentException("Request cannot be NULL"); }

        String body;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    /**
     * Workaround for those situations when we used logging and therefore buffer is already clean
     *
     * @param request Request object
     * @return saved at attribute body
     */
    public static String requestBody(HttpServletRequest request){
        try{
            RequestLogInfo info = (RequestLogInfo) request.getAttribute(RequestLoggingService.INFO);
            return info.getRequestBody();
        }catch (Exception e){
            return "";
        }
    }

    public static boolean isAttributePresent(String key, HttpServletRequest request){
        return (request.getAttribute(key) != null);
    }
}
