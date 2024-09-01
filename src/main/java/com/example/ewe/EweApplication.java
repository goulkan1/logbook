package com.example.ewe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EweApplication {

	public static void main(String[] args) {
		SpringApplication.run(EweApplication.class, args);
	}

}
