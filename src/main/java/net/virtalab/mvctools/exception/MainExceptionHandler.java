package net.virtalab.mvctools.exception;

import net.virtalab.mvctools.internal.exception.NoSuchResourceException;
import net.virtalab.mvctools.logger.RequestLoggingService;
import net.virtalab.mvctools.render.AppErr;
import org.springframework.expression.AccessException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles exceptions
 */
@ControllerAdvice
@EnableWebMvc
@SuppressWarnings("UnusedDeclaration")
public class MainExceptionHandler {

    @ExceptionHandler(AccessException.class)
    public ModelAndView handle403(AccessException e) {
        return AppErr.render(e.getMessage(), 403);
    }

    @ExceptionHandler(NoSuchResourceException.class)
    public ModelAndView handle404(NoSuchResourceException e) {
        return AppErr.render(e.getMessage(), 404);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleMethodError(HttpRequestMethodNotSupportedException e) {
        return AppErr.render(e.getMessage(), 405);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView catchAllExceptions(Exception e, HttpServletRequest request) {
        request.setAttribute(RequestLoggingService.EXCEPTION, e);
        String message = "Internal Server Error";
        return AppErr.render(message, 500);
    }
}
