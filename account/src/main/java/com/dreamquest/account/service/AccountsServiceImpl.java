package com.dreamquest.account.service;

import com.dreamquest.account.dto.CustomerDto;
import com.dreamquest.account.repository.AccountsRepo;
import com.dreamquest.account.repository.CustomerRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountsServiceImpl implements IAccountsService {

    AccountsRepo accountsRepo;
    CustomerRepo customerRepo;

    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
