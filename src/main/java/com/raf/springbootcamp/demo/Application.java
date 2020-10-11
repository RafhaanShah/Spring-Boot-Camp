package com.raf.springbootcamp.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        setPort();
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Application up and running");
        };
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
