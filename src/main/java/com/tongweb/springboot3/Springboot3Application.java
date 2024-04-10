package com.tongweb.springboot3;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot3Application {

  private static final Logger logger = LogManager.getLogger(Springboot3Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Springboot3Application.class, args);
    logger.debug("Debug log message");
    logger.info("Info log message");
    logger.error("Error log message");
    logger.warn("Warn log message");
    logger.trace("Trace log message");
  }

}
