package net.virtalab.mvctools.render;

import net.virtalab.mvctools.logger.RequestLogger;
import net.virtalab.vson.Parser;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Our Standard Output
 */

public class AppOut implements View {

    @Override
    public String getContentType() {
        return "application/json";
    }

    public static ModelAndView render(Object result, int status){

        ModelAndView rslt = new ModelAndView();
        rslt.addObject("out", result);
        rslt.addObject("status",status);
        rslt.setViewName("out");

        return rslt;
    }

    public static ModelAndView render(Object result){
        return render(result,200);
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object outObj = model.get("out");
        Class type =  outObj.getClass();

        String resultingJson = Parser.toJson(outObj,type);
        response.setStatus(200);
        response.setContentType(getContentType());
        response.getWriter().print(resultingJson);
        //Logging
        request.setAttribute(RequestLogger.RESPONSE_BODY,resultingJson);
    }
}
