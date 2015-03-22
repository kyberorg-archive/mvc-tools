package net.virtalab.mvctools.render;

/**
 * Created by IDEA 14
 * Author: Alex Muravya <asm@virtalab.net>
 * Date: 22/Mar/2015
 * Time: 22:22
 */

import net.virtalab.vson.Parser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Class creates Spring's ModelAndView object using Builder Pattern
 */
@SuppressWarnings("UnusedDeclaration")
public class Reply implements View {

    //const
    private static final String REPLY_KEY = "Reply";

    //object
    private HttpStatus status;
    private MediaType contentType;

    private Object body;
    private BodyType bodyType;

    private Map<String,String> headers = new HashMap<>();
    private List<Cookie> cookies = new ArrayList<>();

    private Locale locale;
    private int contentLength;
    private String characterEncoding;

    //defaults
    private final HttpStatus DEFAULT_STATUS = HttpStatus.OK;
    private final MediaType DEFAULT_CONTENT_TYPE = MediaType.TEXT_PLAIN;
    private final BodyType DEFAULT_BODY_TYPE = BodyType.PLAIN;
    private final int DEFAULT_CONTENT_LENGTH = -1;

    public Reply(){}

    /**
     * Initially make instance of Reply class
     *
     * @return Reply object for construction
     */
    public static Reply create(){
        Reply instance = new Reply();
        return instance;
    }

    /**
     * Sets valid HTTP Status Code (@see http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html).
     * By default status is 200 Success
     *
     * @param status valid HTTP status code
     * @return Reply instance
     */
    public Reply status(HttpStatus status){
        this.status = status;
        return this;
    }

    /**
     * Sets MIME type of content. This will be passed as value of header Content-Type.
     * Default type: text/plain
     *
     * @param contentType valid MIME type, which corresponds with body
     * @return Reply instance
     */
    public Reply contentType(MediaType contentType){
        this.contentType = contentType;
        return this;
    }

    /**
     * Sets Content-Length header value. Should be always positive.
     * If negative or 0 given, header won't passed to response.
     *
     * @param length number of bytes in body
     * @return Reply instance
     */
    public Reply contentLength(int length){
        this.contentLength = length;
        return this;
    }

    /**
     * Content encoding, if you need to override default one.
     *
     * @param encoding charset
     * @return Reply instance
     */
    public Reply characterEncoding(String encoding){
        this.characterEncoding = encoding;
        return this;
    }

    /**
     * Content passed to client (browser, application...). Could be any java object.
     * If body type is Json, when we try to make Json from passed object, else we just call toString()
     * When empty body (NULL pointer) supplied body won't be written to response and body type will be useless.
     *
     * @param body content passed to client or Object which should be parsed to Json
     * @return Reply instance
     */
    public Reply body(Object body){
        this.body = body;
        return this;
    }

    /**
     * As in body you can pass any java object, we need to decide should this object be parsed to JSON String or just passed as is.
     * Default is PLAIN.
     *
     * @param type JSON or PLAIN
     * @return  Reply
     */
    public Reply bodyType(BodyType type){
        this.bodyType = type;
        return this;
    }

    /**
     * Adds single header
     *
     * @param name header name
     * @param value header value
     * @return Reply instance
     */
    public Reply header(String name, String value){
        this.headers.put(name,value);
        return this;
    }

    /**
     * Adds multiple headers as String-String combination aka key-value
     * @param headerMap map with String,String (key-value) pairs
     * @return Reply instance
     */
    public Reply headers(Map<String, String> headerMap){
        this.headers.putAll(headerMap);
        return this;
    }

    /**
     * Adds single cookie object
     * @param cookie cookie object
     * @return Reply
     */
    public Reply cookie(Cookie cookie){
        this.cookies.add(cookie);
        return this;
    }

    /**
     * Adds several Cookie objects
     * @param cookies collection contains Cookie objects
     * @return Reply instance
     */
    public Reply cookies(Collection<Cookie> cookies){
        this.cookies.addAll(cookies);
        return this;
    }

    /**
     * Sets the locale of the response
     * @param locale the locale of the response
     * @return Reply instance
     */
    public Reply locale(Locale locale){
        this.locale = locale;
        return this;
    }

    /**
     * Checks object and adds defaults, if needed
     */
    private void applyDefaults(){
        //if absent - add defaults
        if(status==null){status = DEFAULT_STATUS; }

        if(contentLength<=0){
            contentLength = DEFAULT_CONTENT_LENGTH;
        }

        if(bodyType==null){ bodyType = DEFAULT_BODY_TYPE; }

        if(contentType==null){
            if(bodyType == BodyType.JSON){
                contentType = MediaType.APPLICATION_JSON;
            } else {
                contentType = MediaType.TEXT_PLAIN;
            }
        }

        //if custom type is not Json - we adjust flag
        if(contentType == MediaType.APPLICATION_JSON){
            bodyType = BodyType.JSON;
        }
    }

    /**
     * Creates ModelAndView object, which can be passed as return of controller.
     * This object includes Reply object with prev. saved params inside.
     *
     * @return ModelAndView object with parcel contains reply object with prev. saved params
     */
    public ModelAndView render(){
        //prepare self
        applyDefaults();

        //create ModelAndView
        ModelAndView mv = new ModelAndView();
        mv.addObject(REPLY_KEY,this);
        //should be the same as in render.xml id attr
        mv.setViewName("reply");
        return mv;
    }

    @Override
    public String getContentType() {
        return contentType.toString();
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object replyObject = model.get(REPLY_KEY);
        Reply reply = (Reply) replyObject;

        //write response
        response.setStatus(reply.status.value());
        response.setContentType(reply.contentType.toString());

        for(String h: reply.headers.keySet()){
            response.addHeader(h,reply.headers.get(h));
        }

        for(Cookie c: reply.cookies){
            response.addCookie(c);
        }

        if(reply.locale!=null){
            response.setLocale(reply.locale);
        }

        if(reply.contentLength > 0){
            response.setContentLength(reply.contentLength);
        }

        if(reply.characterEncoding!=null){
            response.setCharacterEncoding(reply.characterEncoding);
        }

        //body
        if(reply.body!=null) {
            StringBuilder bodyBuilder = new StringBuilder();
            //if json parse object, else toString()
            if (reply.bodyType == BodyType.JSON) {
                bodyBuilder.append(Parser.toJson(reply.body, reply.body.getClass()));
            } else {
                bodyBuilder.append(reply.body.toString());
            }

            response.getWriter().println(bodyBuilder.toString());
        }
    }

    /**
     * Defines content type of body send to client.
     * if Json chosen, Reply class will try to create JSON String from given Object
     */
    public enum BodyType{
        JSON,
        PLAIN
    }
}
