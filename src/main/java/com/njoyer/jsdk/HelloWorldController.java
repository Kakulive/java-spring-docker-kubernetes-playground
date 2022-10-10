package com.njoyer.jsdk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello, welcome to my Micro Service";
    }
}
