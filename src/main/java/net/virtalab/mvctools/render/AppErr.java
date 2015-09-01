package net.virtalab.mvctools.render;

import net.virtalab.mvctools.logger.RequestLoggingService;
import net.virtalab.mvctools.render.type.ErrorJson;
import net.virtalab.vson.Parser;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Our standard error
 */

public class AppErr implements View {

    @Override
    public String getContentType() {
        return "application/json";
    }

    public static ModelAndView render(int status) {
        return render("", status);
    }

    public static ModelAndView render(String message, int status) {
        ErrorJson json = new ErrorJson();
        json.error.http_code = status;
        json.error.message = message;

        ModelAndView rslt = new ModelAndView();
        rslt.addObject("out", json);
        rslt.addObject("status", status);
        rslt.setViewName("err");

        return rslt;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object outObj = model.get("out");
        int http_code = (Integer) model.get("status");

        Class type = outObj.getClass();

        String resultingJson = Parser.toJson(outObj, type);
        response.setStatus(http_code);
        response.setContentType(getContentType());
        response.getWriter().print(resultingJson);
        //Logging
        request.setAttribute(RequestLoggingService.RESPONSE_BODY, resultingJson);
    }
}
