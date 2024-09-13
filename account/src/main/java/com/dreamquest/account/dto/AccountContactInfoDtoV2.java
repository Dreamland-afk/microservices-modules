package com.dreamquest.account.dto;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;



@Configuration
@Data
@ToString
@ConfigurationProperties(prefix = "accounts")
public class AccountContactInfoDtoV2 {

    private String message;
    private Map<String,String> contactDetails;
    private List<String> onCallSupport;

}
