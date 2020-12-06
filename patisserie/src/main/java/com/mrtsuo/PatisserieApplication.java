package com.mrtsuo;

import java.time.Instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PatisserieApplication {
	@GetMapping("/time")
    public String getCurrentTime() {

        return Instant.now().toString();
    }
	public static void main(String[] args) {
		SpringApplication.run(PatisserieApplication.class, args);
	}
}
