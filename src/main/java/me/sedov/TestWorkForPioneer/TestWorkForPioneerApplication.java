package me.sedov.TestWorkForPioneer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@EnableScheduling
public class TestWorkForPioneerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestWorkForPioneerApplication.class, args);
	}

}
