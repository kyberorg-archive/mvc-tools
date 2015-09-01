package net.virtalab.mvctools.routing;

import net.virtalab.mvctools.internal.exception.NoSuchResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * Class description.
 */
@Controller
@Qualifier("default")
public class DefaultController {
    private final RequestMappingHandlerMapping handlerMapping;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public DefaultController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @RequestMapping()
    public ModelAndView handleAll(HttpServletRequest request) throws HttpRequestMethodNotSupportedException {
        String uri = request.getRequestURI();
        String method = request.getMethod();

        Map<RequestMappingInfo, HandlerMethod> allMappings = this.handlerMapping.getHandlerMethods();

        boolean resourceFound = false;

        for(RequestMappingInfo mapping : allMappings.keySet()) {
            Set<String> mappingUris = mapping.getPatternsCondition().getPatterns();
            for(String mappingUri : mappingUris) {
                if(uri.equalsIgnoreCase(mappingUri)) {
                    resourceFound = true;
                    break;
                }
            }
        }

        //is 404 or 405 here ?
        if(resourceFound) {
            //405
            throw new HttpRequestMethodNotSupportedException(method);
        } else {
            //404
            throw new NoSuchResourceException(uri);
        }
    }

}
