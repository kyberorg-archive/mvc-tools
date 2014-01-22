package net.virtalab.mvctools.internal.exception;

/**
 * 405 Exception
 */
public class WrongMethodException extends RuntimeException {
    private String validMethod;

    public WrongMethodException(String validMethod){
        super();
        this.validMethod = validMethod;
    }

    public String getValidMethod(){
        return this.validMethod;
    }
}
