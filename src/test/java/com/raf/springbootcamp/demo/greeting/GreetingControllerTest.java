package com.raf.springbootcamp.demo.greeting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String path = "/hello";

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetHello() throws Exception {
        Greeting greetingA = objectMapper.readValue(mvc.perform(MockMvcRequestBuilders.get(path)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), Greeting.class);

        Greeting greetingB = objectMapper.readValue(mvc.perform(MockMvcRequestBuilders.get(path)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), Greeting.class);

        assertEquals("Hello, World!", greetingA.getContent());
        assertEquals(greetingA.getId() + 1, greetingB.getId());
    }

    @Test
    public void testGetHello_withNamedParam() throws Exception {
        Greeting greeting = objectMapper.readValue(mvc.perform(MockMvcRequestBuilders.get(path)
                .queryParam("name", "Jon")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), Greeting.class);

        assertEquals("Hello, Jon!", greeting.getContent());
    }

}
