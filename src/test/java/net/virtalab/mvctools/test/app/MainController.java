package net.virtalab.mvctools.test.app;

import com.google.gson.Gson;
import net.virtalab.mvctools.ServletTools;
import net.virtalab.mvctools.render.AppErr;
import net.virtalab.mvctools.render.AppOut;
import net.virtalab.mvctools.test.app.struct.DivisionResult;
import net.virtalab.mvctools.test.app.struct.DivisionStruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Main Controller of test application
 *
 * @author Alexander Muravya {@literal <muravya@corp.sputnik.ru>}
 * @since 2.2
 */
@SuppressWarnings("unused")
@Controller
public class MainController {

    @RequestMapping(value = "/divisor", method = RequestMethod.POST)
    public ModelAndView divide(HttpServletRequest request) throws Exception {
        String rawBody = ServletTools.getRequestBody(request);
        DivisionStruct ds = new Gson().fromJson(rawBody, DivisionStruct.class);
        if(ds == null) {
            return AppErr.render(400);
        }

        DivisionResult rslt = new DivisionResult();

        rslt.result = ds.numerator / ds.denominator;
        if(rslt.result == Double.POSITIVE_INFINITY || rslt.result == Double.NEGATIVE_INFINITY) {
            throw new ArithmeticException("Division by Zero");
        }

        return AppOut.render(rslt, 200);
    }
}
