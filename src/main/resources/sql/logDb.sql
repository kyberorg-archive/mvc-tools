CREATE  TABLE `erk`.`log` (
  `log_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `log_timestamp` BIGINT NOT NULL ,
  `log_serve_time` INT(10) NOT NULL ,
  `log_request_ip` VARCHAR(46) NOT NULL ,
  `log_request_uri` TEXT NOT NULL ,
  `log_request_body` MEDIUMTEXT NOT NULL ,
  `log_response_body` MEDIUMTEXT NOT NULL ,
  `log_response_status` INT(5) NOT NULL ,
  `log_stacktrace` MEDIUMTEXT NULL ,
  PRIMARY KEY (`log_id`) ,
  UNIQUE INDEX `log_id_UNIQUE` (`log_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Log Requests';
