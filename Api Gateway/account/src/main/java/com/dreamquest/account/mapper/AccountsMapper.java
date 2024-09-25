package com.dreamquest.account.mapper;

import com.dreamquest.account.dto.AccountsDto;
import com.dreamquest.account.dto.CustomerDto;
import com.dreamquest.account.entity.Accounts;
import com.dreamquest.account.entity.Customer;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto)
    {

        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }


    public static Accounts maptoAccounts(AccountsDto accountsDto, Accounts accounts)
    {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
