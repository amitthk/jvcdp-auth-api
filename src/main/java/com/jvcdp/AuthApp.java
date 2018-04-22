package com.jvcdp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@EnableConfigurationProperties
public class AuthApp extends SpringBootServletInitializer
{
    private static final Logger log = LoggerFactory.getLogger(AuthApp.class);

    public static void main( String[] args )
    {
        log.info("Starting Application JVCDP Auth-api!");
    	ConfigurableApplicationContext context = SpringApplication.run(AuthApp.class, args);
    	log.info("Started Context: "+ context.getApplicationName());
    }

}
