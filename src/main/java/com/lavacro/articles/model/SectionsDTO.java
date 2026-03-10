package com.lavacro.articles.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SectionsDTO {
	private Integer section;
	private String title;
	private List<ParagraphsDTO> paragraphs;
}
