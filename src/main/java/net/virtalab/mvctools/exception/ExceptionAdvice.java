package net.virtalab.mvctools.exception;

import net.virtalab.mvctools.render.AppErr;
import org.springframework.expression.AccessException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.reflect.Method;

/**
 * Handles exceptions
 */
@ControllerAdvice
@EnableWebMvc
public class ExceptionAdvice  {

    //@Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("EXCEPTION");
    }

    @ExceptionHandler(AccessException.class)
    public ModelAndView handle403(AccessException e){
        return AppErr.render(e.getMessage(), 403);
    }

    @ExceptionHandler(NoSuchResourceException.class)
    public ModelAndView handle404(NoSuchResourceException e){
        return AppErr.render(e.getMessage(), 404);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleMethodError(HttpRequestMethodNotSupportedException e){
        return AppErr.render(e.getMessage(),405);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ModelAndView handle406(HttpMediaTypeNotSupportedException e){
        String message = e.getMessage();
        return AppErr.render(message,406);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralError(Exception e){
        System.out.println("General exception "+e.getClass());
        String message = "Internal Server Error";
        return AppErr.render(message,500);
    }
}
