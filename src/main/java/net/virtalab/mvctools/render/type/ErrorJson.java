package net.virtalab.mvctools.render.type;

/**
 * Class is represents error object
 */
public class ErrorJson {
    public Error error = new Error();

    public static class Error{
        public int http_code;
        public String message;
    }
}
