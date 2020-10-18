package com.raf.springbootcamp.demo.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static java.util.Objects.requireNonNull;

@ActiveProfiles({"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTest {

    private String baseUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        baseUrl = new URL("http://localhost:" + port + "/").toString();
    }

    @Test
    public void testHealthCheck() throws JsonProcessingException {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "UP");
        String expected = new ObjectMapper().writeValueAsString(map);

        ResponseEntity<String> response = template.getForEntity(baseUrl + "/actuator/health", String.class);
        Assertions.assertEquals(expected, response.getBody());
    }

    @Test
    public void testGeneratingApiJson() throws Exception {
        ResponseEntity<String> response = template.getForEntity(baseUrl + "api", String.class);
        Files.write(Paths.get("./docs/api.json"), requireNonNull(response.getBody()).getBytes());
    }
}
