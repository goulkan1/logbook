package com.example.ewe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
    private final ExampleFeignClient exampleFeignClient;

    public EchoController(ExampleFeignClient exampleFeignClient) {
        this.exampleFeignClient = exampleFeignClient;
    }

    @GetMapping("/echo/{message}")
    public String echo(@PathVariable String message) {

        return message;
    }

    @PostMapping("/post/{message}")
    public String postMethodName(@PathVariable String message) {
        return "Received: " + message;
    }

    @GetMapping("/err")
    public ResponseEwe create2() throws Exception {
        throw new IllegalArgumentException("This is an error message");
    }

    @GetMapping("/rickandmorty")
    public ResponseEwe create(String asd) {
        ResponseEwe response = exampleFeignClient.getData();
        response.setPassword("ewe");
        return response;
    }

}
