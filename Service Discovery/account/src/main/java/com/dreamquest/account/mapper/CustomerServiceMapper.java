package com.dreamquest.account.mapper;

import com.dreamquest.account.dto.CustomerDetailsDto;
import com.dreamquest.account.dto.CustomerDto;
import com.dreamquest.account.entity.Customer;

public class CustomerServiceMapper {

    public static CustomerDetailsDto mapToCustomerServiceDto(CustomerDto customerDto, CustomerDetailsDto customerDetailsDto)
    {
        customerDetailsDto.setName(customerDto.getName());
        customerDetailsDto.setEmail(customerDto.getEmail());
        customerDetailsDto.setMobileNumber(customerDto.getMobileNumber());
        customerDetailsDto.setAccountsDto(customerDto.getAccountsDto());
        return customerDetailsDto;
    }
}
