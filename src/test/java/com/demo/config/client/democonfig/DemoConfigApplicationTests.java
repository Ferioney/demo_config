package com.demo.config.client.democonfig;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("prod")
class DemoConfigApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Application starter");
	}

}
