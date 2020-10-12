package com.raf.springbootcamp.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Hello")
public class HelloController {

    @GetMapping("/")
    @ApiOperation("Says 'Hello'")
    public String getHello(@RequestParam(value = "name", defaultValue = "World", required = false) String name) {
        return String.format("Hello, %s!", name);
    }

}
