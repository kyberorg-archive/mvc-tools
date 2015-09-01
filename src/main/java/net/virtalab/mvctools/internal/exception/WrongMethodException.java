package net.virtalab.mvctools.internal.exception;

/**
 * 405 Exception
 */
class WrongMethodException extends RuntimeException {
    private final String validMethod;

    public WrongMethodException(String validMethod) {
        super();
        this.validMethod = validMethod;
    }

    public String getValidMethod() {
        return this.validMethod;
    }
}
