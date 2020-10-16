package com.raf.springbootcamp.demo;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Autowired
    private BuildProperties buildProperties;

    @Value( "${base-server}")
    private String baseServer;

    @Value( "${profile}")
    private String profile;

    @Value("${server.port:8080}")
    int port;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url(profile.equals("dev") ? baseServer + ":" + port : baseServer))
                .info(new Info().title("Spring Boot Demo")
                        .description("Spring Boot sample application")
                        .version(buildProperties.getVersion())
                        .license(new License().name("License").url("https://github.com/RafhaanShah/Spring-Boot-Camp")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Boot Demo Documentation")
                        .url("https://github.com/RafhaanShah/Spring-Boot-Camp"));
    }

}
