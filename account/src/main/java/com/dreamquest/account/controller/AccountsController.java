package com.dreamquest.account.controller;

import com.dreamquest.account.Constants.AccountsConstants;
import com.dreamquest.account.dto.AccountsDto;
import com.dreamquest.account.dto.CustomerDto;
import com.dreamquest.account.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController
{

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto)
    {
        return new ResponseEntity<>(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201), HttpStatus.CREATED);
    }
}
