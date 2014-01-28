package net.virtalab.mvctools.render;

import net.virtalab.mvctools.Result;
import net.virtalab.mvctools.logger.RequestLoggingService;
import net.virtalab.vson.Parser;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Our Standard Output
 */
@SuppressWarnings("UnusedDeclaration")
public class AppOut implements View {

    public static final String OUT = "out";
    public static final String STATUS = "status";


    @Override
    public String getContentType() {
        return "application/json";
    }

    public static ModelAndView render(Object result, int status){

        ModelAndView rslt = new ModelAndView();
        rslt.addObject(OUT, result);
        rslt.addObject(STATUS,status);
        rslt.setViewName("out");

        return rslt;
    }

    public static ModelAndView render(Object result){
        return render(result,200);
    }

    public static ModelAndView render(Result result){
        return render(result.get(),result.getStatus());
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object outObj = model.get(OUT);
        int status = (Integer) model.get(STATUS);
        Class type =  outObj.getClass();

        String resultingJson = Parser.toJson(outObj,type);
        response.setStatus(status);
        response.setContentType(getContentType());
        response.getWriter().print(resultingJson);
        //Logging
        request.setAttribute(RequestLoggingService.RESPONSE_BODY,resultingJson);
    }
}
