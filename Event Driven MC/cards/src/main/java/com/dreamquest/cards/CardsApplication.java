package com.dreamquest.cards;

import com.dreamquest.cards.dto.AccountContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "DreamBank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Swapnadeep Mondal",
						email = "swapnadeep407@gmail.com",
						url = "https://www.dreamquest.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.dreamquest.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "DreamBank Cards microservice REST API Documentation",
				url = "http://127.0.0.1:9000/swagger-ui.html"
		)
)
@EnableConfigurationProperties(AccountContactInfoDto.class)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
