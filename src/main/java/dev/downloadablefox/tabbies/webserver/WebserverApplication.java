package dev.downloadablefox.tabbies.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan

public class WebserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebserverApplication.class, args);
	}
}
