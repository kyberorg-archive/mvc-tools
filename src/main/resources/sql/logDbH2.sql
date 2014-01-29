CREATE TABLE IF NOT EXISTS log (
  log_id BIGINT PRIMARY KEY auto_increment ,
  log_timestamp BIGINT NOT NULL ,
  log_serve_time INT(10) NOT NULL ,
  log_request_ip VARCHAR(46) NOT NULL ,
  log_request_uri CLOB NOT NULL ,
  log_request_body CLOB NOT NULL ,
  log_response_body CLOB NOT NULL ,
  log_response_status TINYINT NOT NULL ,
  log_stacktrace CLOB NULL
);

//Optional
//CREATE USER IF NOT EXISTS logger PASSWORD 'pass';
//GRANT ALL ON log TO logger;