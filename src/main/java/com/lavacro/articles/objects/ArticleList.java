package com.lavacro.articles.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleList implements Comparable<ArticleList> {
	private Integer id;
	private String title;
	private String urlText;

	public int compareTo(ArticleList articleList) {
		return title.compareTo(articleList.title);
	}
}
