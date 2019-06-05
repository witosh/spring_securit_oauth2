package com.github.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
public class ResourcesServerApplication{

	public static void main(String[] args) {
		SpringApplication.run(ResourcesServerApplication.class, args);
	}

}
