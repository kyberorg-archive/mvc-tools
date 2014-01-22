package net.virtalab.mvctools.exception;

/**
 * 404
 */
public class NoSuchResourceException extends RuntimeException {
    private String message;

    public NoSuchResourceException(String uri){
        super();
        this.message = "Requested resource "+uri+" not found";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
