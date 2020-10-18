package com.raf.springbootcamp.demo.greeting;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@Tag(name = "greeting", description = "Returns a greeting")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/hello")
    @Operation(summary = "Says 'Hello'")
    public Greeting getHello(@RequestParam(value = "name", defaultValue = "World", required = false) String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
