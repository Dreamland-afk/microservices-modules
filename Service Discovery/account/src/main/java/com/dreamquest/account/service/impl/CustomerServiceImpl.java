package com.dreamquest.account.service.impl;

import com.dreamquest.account.dto.CardsDto;
import com.dreamquest.account.dto.CustomerDetailsDto;
import com.dreamquest.account.dto.CustomerDto;
import com.dreamquest.account.dto.LoansDto;
import com.dreamquest.account.mapper.CustomerServiceMapper;
import com.dreamquest.account.service.IAccountsService;
import com.dreamquest.account.service.ICustomersService;
import com.dreamquest.account.service.feignclients.CardsFeignClient;
import com.dreamquest.account.service.feignclients.LoansFeignClients;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements ICustomersService {


    IAccountsService iAccountsService;
    CardsFeignClient cardsFeignClient;
    LoansFeignClients loansFeignClients;



    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {

        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);

        CustomerDetailsDto customerDetailsDto = CustomerServiceMapper.mapToCustomerServiceDto(customerDto, new CustomerDetailsDto());

        ResponseEntity<CardsDto> fetchedCardDetails = cardsFeignClient.fetchCard(mobileNumber);
        ResponseEntity<LoansDto> fetchedLoanDetails = loansFeignClients.fetchLoanDetails(mobileNumber);

        customerDetailsDto.setCardsDto(fetchedCardDetails.getBody());
        customerDetailsDto.setLoansDto(fetchedLoanDetails.getBody());


        return customerDetailsDto;
    }
}
