package net.virtalab.mvctools.render.result;

/**
 * Result is status + Object which we should to render
 */
@SuppressWarnings("UnusedDeclaration")
public class ResultImpl implements Result {
    public int status;
    public Object resultingObject;

    public int getStatus() {
        return status;
    }

    @Override
    public Object get() {
        return resultingObject;
    }
}
