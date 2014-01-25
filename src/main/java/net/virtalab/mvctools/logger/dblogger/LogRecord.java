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

    static {
        validatePresenceOf(TIMESTAMP).message("TimeStamp is absent");
        validatePresenceOf(SERVED).message("Served at data is absent");
        validatePresenceOf(REQUEST_IP).message("Request IP is absent");
        validatePresenceOf(REQUEST_URI).message("Request URI is absent");
        //validatePresenceOf(REQUEST_BODY).message("Request Body is absent");
        validatePresenceOf(RESPONSE_BODY).message("Response Body is absent");
        validatePresenceOf(RESPONSE_STATUS).message("Response Status is absent");
    }
}
