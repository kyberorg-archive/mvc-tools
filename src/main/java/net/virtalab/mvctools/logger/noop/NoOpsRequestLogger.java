package net.virtalab.mvctools.logger.noop;

import net.virtalab.mvctools.logger.RequestLogInfo;
import net.virtalab.mvctools.logger.RequestLogger;

/**
 * No operational logger. Can used as Stub.
 */
@SuppressWarnings("UnusedDeclaration")
public class NoOpsRequestLogger implements RequestLogger {
    @Override
    public void log(RequestLogInfo info) {
        //do nothing :)
    }
}
