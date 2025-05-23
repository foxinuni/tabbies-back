package dev.downloadablefox.tabbies.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackages = "dev.downloadablefox.tabbies.webserver.entities")
@EnableJpaRepositories(basePackages = "dev.downloadablefox.tabbies.webserver.repositories")

public class WebserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebserverApplication.class, args);
	}
}
