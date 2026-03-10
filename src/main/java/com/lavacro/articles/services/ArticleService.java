package com.lavacro.articles.services;

import com.lavacro.articles.model.ArticleDTO;
import com.lavacro.articles.repository.ArticlesRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
	private final ArticlesRepository articlesRepository;
	private final MongoTemplate mongoTemplate;

	ArticleService(ArticlesRepository articlesRepository, MongoTemplate mongoTemplate) {
		this.articlesRepository = articlesRepository;
		this.mongoTemplate = mongoTemplate;
	}

	public ArticleDTO getArticleByTitle(final String title)  {
		List<ArticleDTO> entries = articlesRepository.findByUrlText(title);
		return entries.isEmpty() ? null : entries.getFirst();
	}

	public List<ArticleDTO> getArticleIdsAndTitles(final Integer exception) {
		return articlesRepository.findAllExceptFor(exception);
	}

	public ArticleDTO getLatestArticle() {
		Query query = new Query(Criteria.where("active").ne(false));
		query.with(Sort.by(Sort.Direction.DESC, "published"));
		query.limit(1);

		return mongoTemplate.findOne(query, ArticleDTO.class);
	}
}
