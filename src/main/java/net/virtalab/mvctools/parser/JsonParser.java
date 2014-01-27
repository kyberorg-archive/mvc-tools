package net.virtalab.mvctools.parser;

import com.google.gson.JsonSyntaxException;
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
@SuppressWarnings("UnusedDeclaration")
public class JsonParser {
    public static <T> T parse(String json, Type typeOfT) throws ParserException, VsonException{
        T parsedObject = null;
        ModelAndView mv = null;
        try{
            parsedObject = Parser.fromJson(json,typeOfT);
        }catch (NoJsonFoundException | JsonSyntaxException e){
            mv = AppErr.render("Payload must be valid JSON hash", 400);
        }catch (WrongJsonStructureException e1){
            mv = AppErr.render(e1.getMessage(), 422);
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
     * @throws com.google.gson.JsonSyntaxException when passed Json has got wrong syntax
     * @throws WrongJsonStructureException when passed string violates object structure
     * @throws VsonException when internal parser error occurred
     */
    public static <T> T pars(String json, Type typeOfT) throws NoJsonFoundException, JsonSyntaxException, WrongJsonStructureException, VsonException{
        return Parser.fromJson(json,typeOfT);
    }

}
