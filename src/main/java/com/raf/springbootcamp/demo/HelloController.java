package com.raf.springbootcamp.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello", description = "Hello operations")
public class HelloController {

    @GetMapping("/")
    @Operation(summary = "Says 'Hello'")
    public String getHello(@RequestParam(value = "name", defaultValue = "World", required = false) String name) {
        return String.format("Hello, %s!", name);
    }

}
