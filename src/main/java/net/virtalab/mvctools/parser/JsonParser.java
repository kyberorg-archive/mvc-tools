package net.virtalab.mvctools.parser;

import net.virtalab.mvctools.render.AppErr;
import net.virtalab.vson.Parser;
import net.virtalab.vson.exception.NoJsonFoundException;
import net.virtalab.vson.exception.VsonException;
import net.virtalab.vson.exception.WrongJsonStructureException;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Type;

/**
 * Parser for incoming json
 */
public class JsonParser {
    public static <T> T parse(String json, Type typeOfT) throws ParserException{
        T parsedObject = null;
        ModelAndView mv = null;
        try{
            parsedObject = Parser.fromJson(json,typeOfT);
        }catch (NoJsonFoundException e){ //add here multi-catch of SyntaxException
            //payload must be JSON hash
            mv = AppErr.render("Payload must be valid JSON hash", 400);
        }catch (WrongJsonStructureException e1){
            //422 + error message
            mv = AppErr.render(e1.getMessage(), 422);
        }catch (VsonException e){
            //internal 500
            mv = AppErr.render("Internal Error", 500);
        }
        if(mv != null){ throw new ParserException(mv); }
        return parsedObject;
    }

    /**
     * Just makes parsing without handling any exceptions internally
     *
     * @param json String that is subject of parsing
     * @param typeOfT type of target object
     * @param <T> generic type of target object
     * @return object of typeOfT if parsing successful
     * @throws NoJsonFoundException when passed string is not JSON
     * @throws WrongJsonStructureException when passed string violates object structure
     * @throws VsonException when internal parser error occurred
     */
    public static <T> T pars(String json, Type typeOfT) throws NoJsonFoundException, WrongJsonStructureException, VsonException{
        return Parser.fromJson(json,typeOfT);
    }

}
