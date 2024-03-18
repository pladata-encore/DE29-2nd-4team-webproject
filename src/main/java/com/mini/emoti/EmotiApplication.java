package com.mini.emoti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EmotiApplication {

	// org.springframework.context.ApplicationContextException: Failed to start bean 'webServerStartStop'

	public static void main(String[] args) {
		SpringApplication.run(EmotiApplication.class, args);
	}

}
