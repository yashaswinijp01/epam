package com.epam.library;

import com.epam.library.controller.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LibraryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryServiceApplication.class, args);
	}

	@Autowired
	BookController bookController;



	@Bean
	public RestTemplate getTemplate(){
		return new RestTemplate();
	}

}
