package com.salgado.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.salgado.api.config.property.SalgadomoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(SalgadomoneyApiProperty.class)
public class SalgadomoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalgadomoneyApiApplication.class, args);
	}
}
