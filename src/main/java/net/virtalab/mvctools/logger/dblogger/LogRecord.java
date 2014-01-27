package net.virtalab.mvctools.logger.dblogger;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

/**
 * Log Table Model
 */
@SuppressWarnings("UnusedDeclaration")
@DbName(value = "logDb")
@Table(value = "log")
@IdName(value = "log_id")
public class LogRecord extends Model {
    public static final String ID = "log_id";
    public static final String TIMESTAMP = "log_timestamp";
    public static final String SERVED = "log_serve_time";
    public static final String REQUEST_IP = "log_request_ip";
    public static final String REQUEST_URI = "log_request_uri";
    public static final String REQUEST_BODY = "log_request_body";
    public static final String RESPONSE_BODY = "log_response_body";
    public static final String RESPONSE_STATUS = "log_response_status";
    public static final String STACKTRACE= "log_stacktrace";

}
