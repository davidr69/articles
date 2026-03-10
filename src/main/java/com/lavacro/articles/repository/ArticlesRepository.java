package com.lavacro.articles.repository;

import com.lavacro.articles.model.ArticleDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlesRepository extends MongoRepository<ArticleDTO, String> {
	List<ArticleDTO> findByUrlText(final String urlText);

	@Query(
			value = "{'active': {$ne : false}, 'article': {$ne : ?0} }",
			fields = "{ 'article' : 1, 'title' : 1, 'url_text':1, 'active':1, 'published': 1 }",
			sort = "{'published': -1}"
	)
	List<ArticleDTO> findAllExceptFor(final Integer index);
}
