package com.lavacro.articles.controllers;

import com.lavacro.articles.services.ArticleService;
import com.lavacro.articles.model.ArticleDTO;
import com.lavacro.articles.properties.ApplicationProperites;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	private final ArticleService articleService;
	private final ApplicationProperites appProps;

	HomeController(ArticleService articleService, ApplicationProperites appProps) {
		this.articleService = articleService;
		this.appProps = appProps;
	}

	@GetMapping("/view")
	public String byId(
			Model model,
			HttpServletRequest req,
			@RequestParam(value = "title", required = false) String title
	) {
		LOGGER.info("Request: title='{}'", title);
		String proto = req.getRequestURL().toString().split(":")[0];
		String agent = req.getHeader("User-Agent");

		ArticleDTO articleDTO;

		articleDTO = articleService.getArticleByTitle(title);

		List<ArticleDTO> articleSummaries = articleService.getArticleIdsAndTitles(articleDTO.getArticle());

		model.addAttribute("articleList", articleSummaries);
		model.addAttribute("article", articleDTO);
		model.addAttribute("imageRepo", proto + "://" + appProps.getImageRepo());

		if(agent != null && agent.contains("Mobile")) {
			return "mobile";
		} else {
			return "desktop";
		}
	}

	@GetMapping("/")
	public String noArticle (
		Model model,
		HttpServletRequest req
	) {
		ArticleDTO articleDTO = articleService.getLatestArticle();
		return byId(model, req, articleDTO.getUrlText());
	}
}
