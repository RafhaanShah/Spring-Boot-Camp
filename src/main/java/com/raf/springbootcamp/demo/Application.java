package com.raf.springbootcamp.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        setPort();
        SpringApplication.run(Application.class, args);
        log.info("Application up and running");
    }

    private static void setPort(){
        String ENV_PORT = System.getenv().get("PORT");
        if(ENV_PORT != null) {
            System.getProperties().put("server.port", ENV_PORT);
        } else {
            System.getProperties().put("server.port", "8080");
        }
    }

}
