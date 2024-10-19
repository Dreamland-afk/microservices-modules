package com.dreamquest.account.dto;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Data
public class AccountMsgDto {

    Long accountNumber;String name; String email; String mobileNumber;
}
