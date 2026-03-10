package com.lavacro.articles.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@Document(collection = "sorted_articles")
public class ArticleDTO {
	private final static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);

	@MongoId
	private String id;
	private Integer article;
	private String title;
	private String bgimage;
	private String preview;
	@Field(value = "url_text")
	private String urlText;
	private LocalDate published;
	private List<SectionsDTO> sections;

	@Transient
	public String getPublishedDate() {
		if (published == null) {
			return "";
		}
		return published.format(fmt);
	}
}
