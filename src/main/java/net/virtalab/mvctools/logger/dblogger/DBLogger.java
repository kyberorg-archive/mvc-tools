package net.virtalab.mvctools.logger.dblogger;

import net.virtalab.mvctools.logger.RequestLogInfo;
import net.virtalab.mvctools.logger.RequestLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service that exec logging to database
 */
@Service
public class DBLogger implements RequestLogger {

    @Async
    public void log(RequestLogInfo info){
        //Just for emulation of delay - remove before flight
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
