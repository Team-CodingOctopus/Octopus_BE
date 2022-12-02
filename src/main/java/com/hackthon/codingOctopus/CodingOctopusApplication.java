package com.hackthon.codingOctopus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CodingOctopusApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingOctopusApplication.class, args);
	}

}
