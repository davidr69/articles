package com.lavacro.articles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.lavacro.articles.repository")
public class ArticlesApplication {
	private ArticlesApplication() {
	}

	static void main(String[] args) {
		SpringApplication.run(ArticlesApplication.class, args);
	}
}
