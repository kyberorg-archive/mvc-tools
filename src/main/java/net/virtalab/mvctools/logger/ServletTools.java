package net.virtalab.mvctools.logger;

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

   /* public static String getResponseBody(HttpServletResponse response){
        if(response==null){ throw new IllegalArgumentException("Response cannot be NULL"); }
        String body;
        try{
            OutputStream outputStream = response.getOutputStream();
    System.out.println("OS");

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    System.out.println("After VAR");
            outputStream.write(byteStream.toByteArray());
    System.out.println("After Write");
            body = byteStream.toString();
        } catch (IOException e) {
   System.out.println("IO");
            throw new RuntimeException(e);
        }
        return body;

    }*/
}
