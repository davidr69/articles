package com.lavacro.articles.configuration;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.lang.NonNull;

@Configuration
@Slf4j
@ConfigurationProperties(prefix = "database")
@Getter
@Setter
public class MongoConfig extends AbstractMongoClientConfiguration {
	private String url;
	private String name;
	private String username;
	private String password;

	@NonNull
	protected String getDatabaseName() {
		return name;
	}

	@NonNull
	@Override
	public MongoClient mongoClient() {
		String encodedPassword;
		try {
			encodedPassword = URLEncoder.encode(password, StandardCharsets.UTF_8);
		} catch (Exception e) {
			log.error("Error encoding password: {}", e.getMessage());
			throw new IllegalStateException(e);
		}

		String dbUrl = String.format("mongodb://%s:%s@%s/%s",
				username, encodedPassword, url, name);
		ConnectionString connectionString = new ConnectionString(dbUrl);
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.build();

		MongoClient mongoClient = MongoClients.create(mongoClientSettings);
		try (
			MongoCursor<String> cursor = mongoClient.listDatabaseNames().cursor()
		) {
			while (cursor.hasNext()) {
				String val = cursor.next();
				log.info("Database: {}", val);
			}
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		return mongoClient;
	}
}
