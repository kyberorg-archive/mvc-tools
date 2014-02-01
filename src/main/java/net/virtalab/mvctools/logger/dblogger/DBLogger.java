package net.virtalab.mvctools.logger.dblogger;

import net.virtalab.databazer.NamedDataSource;
import net.virtalab.mvctools.logger.RequestLogInfo;
import net.virtalab.mvctools.logger.RequestLogger;
import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.InitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service that exec logging to database
 */
@Service
public class DBLogger implements RequestLogger {

    private NamedDataSource logDb;

    private boolean loggingEnabled = true;

    @Async
    public void log(RequestLogInfo info){
        if(info == null){
            System.err.println("Cannot operate on NULL info object. Sorry. Check your code on NPE.");
            return;
        }
        if(this.logDb == null) {
            this.loggingEnabled = false;
            System.err.println("ERROR: no bean named 'logDb' is found. Won't log anything. Logging disabled.");
            return;
        }
/*        if(this.logDb.getName().equalsIgnoreCase("logDb")){
            System.err.println("Your Datasource must have name 'logDb'. Current name is: "+this.logDb.getName());
            return;
        }*/
        if(!loggingEnabled){
            //verify is Db connection is still down or not (in case connection may be restored)
            if(this.isDbOnline()){
                System.out.println("It seems like database came back online - resuming logging");
                this.loggingEnabled = true;
            } else {
                return;
            }
        }
        //create record
        try{
            new DB(this.logDb.getName()).open(this.logDb);

            LogRecord rec = new LogRecord();
            rec.set(LogRecord.TIMESTAMP,info.getTimestamp());
            rec.set(LogRecord.SERVED,info.getServedAt());
            rec.set(LogRecord.REQUEST_IP,info.getRequestIp());
            rec.set(LogRecord.REQUEST_URI,info.getRequestUri());
            rec.set(LogRecord.REQUEST_BODY,info.getRequestBody());
            rec.set(LogRecord.RESPONSE_BODY,info.getResponseBody());
            rec.set(LogRecord.RESPONSE_STATUS,info.getResponseStatus());

            if(info.getStackTrace() != null) {
                rec.set(LogRecord.STACKTRACE,info.getStackTrace());
            }
            rec.saveIt();

            new DB(this.logDb.getName()).close();
        } catch (InitException initEx){
            this.loggingEnabled = false;
            System.err.println("Database is not reachable - logging disabled");
        } catch (Exception e){
            System.err.println("Got exception while logging to Database: "+e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    @Qualifier("logDb")
    public void setLogDb(NamedDataSource logDb) {
        this.logDb = logDb;
    }

    private boolean isDbOnline(){
        if(this.logDb==null){ return false; }
        try{
            new DB(this.logDb.getName()).open(this.logDb);
            new DB(this.logDb.getName()).close();
            return true;
        }catch (InitException initEx){
            return false;
        }
    }
}
