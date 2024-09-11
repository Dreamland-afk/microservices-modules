package com.dreamquest.account;

import com.dreamquest.account.dto.AccountContactInfoDto;
import com.dreamquest.account.dto.AccountContactInfoDtoV2;
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
@EnableConfigurationProperties(AccountContactInfoDto.class)
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "DreamBank Accounts microservice REST API Documentation",
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
				description =  "DreamBank Accounts microservice REST API Documentation",
				url = "https://www.dreamquest.com/swagger-ui.html"
		)
)
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);

	}

}


