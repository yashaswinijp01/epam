package com.epam.books;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BookServiceApplicationTests {

	@Test
	void contextLoads(ApplicationContext context) {
		Assertions.assertThat(context).isNotNull();
	}

}
