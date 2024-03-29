package net.virtalab.mvctools;

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
@SuppressWarnings("UnusedDeclaration")
public class ServletTools {
    /**
     * Reads body (as UTF-8) from Request object
     *
     * @param request Request object
     *
     * @return Body as String
     *
     * @throws IOException cannot close stream
     */
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        return getRequestBody(request, "UTF-8");
    }

    /**
     * Reads body from Request object
     *
     * @param request  Request object
     * @param encoding Body encoding
     *
     * @return Body as String
     *
     * @throws IOException cannot close stream
     */
    public static String getRequestBody(HttpServletRequest request, String encoding) throws IOException {

        if(request == null) {
            throw new IllegalArgumentException("Request cannot be NULL");
        }

        String body;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            request.setCharacterEncoding(encoding);
            InputStream inputStream = request.getInputStream();
            if(inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, encoding));
                char[] charBuffer = new char[128];
                int bytesRead;
                while((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if(bufferedReader != null) {
                bufferedReader.close();
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    /**
     * Workaround for those situations when we used logging and therefore buffer is already clean
     *
     * @param request Request object
     *
     * @return saved at attribute body
     */
    public static String requestBodyAfterLogger(HttpServletRequest request) {
        try {
            RequestLogInfo info = (RequestLogInfo) request.getAttribute(RequestLoggingService.INFO);
            return info.getRequestBody();
        } catch(Exception e) {
            return "";
        }
    }

    public static boolean isAttributePresent(String key, HttpServletRequest request) {
        return (request.getAttribute(key) != null);
    }

    /**
     * Reconstruct original requesting URL
     *
     * @param req Received Servlet Request
     *
     * @return full URL
     */
    public static String getFullUrl(HttpServletRequest req) {
        String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80
        String contextPath = req.getContextPath();   // /mywebapp
        String servletPath = req.getServletPath();   // /servlet/MyServlet
        String pathInfo = req.getPathInfo();         // /a/b;c=123
        String queryString = req.getQueryString();          // d=789

        // Reconstruct original requesting URL
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        if((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath).append(servletPath);

        if(pathInfo != null) {
            url.append(pathInfo);
        }
        if(queryString != null) {
            url.append("?").append(queryString);
        }
        return url.toString();
    }
}
