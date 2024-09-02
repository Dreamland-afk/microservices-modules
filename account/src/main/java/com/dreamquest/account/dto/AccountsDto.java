package com.dreamquest.account.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountsDto {


    private Long accountNumber;

    @NotEmpty(message = "AccountType can not be a null or empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress can not be a null or empty")
    private String branchAddress;
}
