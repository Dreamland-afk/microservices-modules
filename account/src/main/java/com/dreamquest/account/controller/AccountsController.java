package com.dreamquest.account.controller;

import com.dreamquest.account.Constants.AccountsConstants;
import com.dreamquest.account.dto.AccountsDto;
import com.dreamquest.account.dto.CustomerDto;
import com.dreamquest.account.dto.ResponseDto;
import com.dreamquest.account.service.AccountsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController
{

    @Autowired
    AccountsServiceImpl accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto)
    {
        accountsService.createAccount(customerDto);
        return new ResponseEntity<>(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201), HttpStatus.CREATED);
    }
}
