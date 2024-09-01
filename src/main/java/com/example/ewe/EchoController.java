package com.example.ewe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/")
    public ResponseEwe create2(String asd) throws Exception {
        throw new Exception();
    }

    @GetMapping("/ewe")
    public ResponseEwe create(String asd) {
        ResponseEwe response = exampleFeignClient.getData();
        response.setPassword("ewe");
        return response;
    }

}
