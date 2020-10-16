package com.raf.springbootcamp.demo;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Spring Boot Demo")
                        .description("Spring Boot sample application")
                        .version(buildProperties.getVersion())
                        .license(new License().name("").url("")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Boot Demo Documentation")
                        .url("https://github.com/RafhaanShah/Spring-Boot-Camp"));
    }

}
