package com.example.mug_backend;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAsync
public class MugBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MugBackendApplication.class, args);
	}

}
