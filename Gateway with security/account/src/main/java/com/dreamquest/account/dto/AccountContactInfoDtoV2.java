package com.dreamquest.account.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@Data
@ConfigurationProperties(prefix = "accounts")
public class AccountContactInfoDtoV2 {

    private String message;
    private Map<String,String> contactDetails;
    private List<String> onCallSupport;


}
