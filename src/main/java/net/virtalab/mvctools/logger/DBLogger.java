package net.virtalab.mvctools.logger;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service that exec logging to database
 */
@Service
public class DBLogger {

    @Async
    public void log(LogInfo info){
        //Just for emulation of delay - remove before flight
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Log to DB
        System.out.println("ASync logging "+info.toLog());
    }
}
