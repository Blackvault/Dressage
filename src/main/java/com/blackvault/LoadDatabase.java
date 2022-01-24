package com.blackvault;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(RiderRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Rider("Bilbo Baggins", "bilbo@shire.com")));
			log.info("Preloading " + repository.save(new Rider("Frodo Baggins", "frodo@shire.com")));
		};
	}
}