package com.example.ewe;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "exampleClient", url = "https://rickandmortyapi.com", configuration = FeignConfig.class)
public interface ExampleFeignClient {

    @GetMapping("/api/episode/28")
    ResponseEwe getData();
}
