package com.challenge.assembly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AssemblyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssemblyApplication.class, args);
	}

}
