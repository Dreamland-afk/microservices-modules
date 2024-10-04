package com.dreamquest.account.service;

import com.dreamquest.account.dto.CustomerDetailsDto;

public interface ICustomersService {

    /**
     * @param mobileNumber      - Input Mobile Number
     * @param correlationHeader
     * @return Customer Details based on a given mobileNumber
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationHeader);
}
