package com.lavacro.articles;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@SpringBootTest
class ArticlesApplicationTests {
	private static Logger logger = LogManager.getLogger(ArticlesApplicationTests.class);

	@Test
	void contextLoads() {
		String multiline = "<xml><root>\n<key>value</key>\n</root></xml>";
		logger.info(multiline);
		logger.info(multiline.replace("\n", ""));
	}
}
