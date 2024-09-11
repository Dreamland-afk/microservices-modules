package com.dreamquest.loans;

import com.dreamquest.loans.audit.AuditorAwareImpl;
import com.dreamquest.loans.dto.AccountContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(AccountContactInfoDto.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
