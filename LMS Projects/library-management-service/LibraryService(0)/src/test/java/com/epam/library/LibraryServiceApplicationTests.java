package com.epam.library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class LibraryServiceApplicationTests {

	@Test
	void contextLoads(ApplicationContext context) {
		Assertions.assertNotNull(context);
	}

}
