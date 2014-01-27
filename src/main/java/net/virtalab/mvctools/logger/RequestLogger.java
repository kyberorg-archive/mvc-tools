package net.virtalab.mvctools.logger;

/**
 * contains log method. We use this interface at LoggingService by autowiring logger impl. Thus allows user choose needed implementation or write own.
 */
public interface RequestLogger {

    public void log(RequestLogInfo info);
}
