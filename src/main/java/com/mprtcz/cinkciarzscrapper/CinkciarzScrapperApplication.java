package com.mprtcz.cinkciarzscrapper;

import com.mprtcz.cinkciarzscrapper.scrapper.Scrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CinkciarzScrapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinkciarzScrapperApplication.class, args);
	}

	@Value("${TARGET:World}")
	String target;

	@RestController
	class HelloworldController {

		@GetMapping("/")
		String hello() {
			return "Hello " + target + "!";
		}

		@GetMapping("/rates")
		ResponseEntity getRates() {
			return ResponseEntity.ok(Scrapper.getRates());
		}
	}

}
