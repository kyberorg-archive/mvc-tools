package net.virtalab.mvctools;

/**
 * Result: Object and status
 */
@SuppressWarnings("UnusedDeclaration")
public class Result {

    private static Result self;

    private Object result;
    private int status;

    public Result() {
        self = this;
    }

    public static Result set(Object rslt, int status) {
        self = new Result();
        self.result = rslt;
        self.status = status;
        return self;
    }

    public static Result set(Object rslt) {
        self = new Result();
        self.result = rslt;
        return self;
    }

    public static Result set(int status) {
        self = new Result();
        self.status = status;
        return self;
    }

    public Result status(int status) {
        return this;
    }

    public Object get() {
        return this.result;
    }

    public int getStatus() {
        return this.status;
    }
}
