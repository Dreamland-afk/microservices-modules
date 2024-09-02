package com.dreamquest.account.service;

import com.dreamquest.account.Constants.AccountsConstants;
import com.dreamquest.account.dto.CustomerDto;
import com.dreamquest.account.entity.Accounts;
import com.dreamquest.account.entity.Customer;
import com.dreamquest.account.exception.CustomerAlreadyExistsException;
import com.dreamquest.account.mapper.CustomerMapper;
import com.dreamquest.account.repository.AccountsRepo;
import com.dreamquest.account.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    AccountsRepo accountsRepo;
    CustomerRepo customerRepo;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> isPersentMobileNumber = customerRepo.findByMobileNumber(customer.getMobileNumber());

        if(isPersentMobileNumber.isPresent())
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number " + customerDto.getMobileNumber());
        customer.setCreatedBy("Anonymous");
        customer.setCreatedAt(LocalDateTime.now());
        customer = customerRepo.save(customer);
        accountsRepo.save(createNewAccount(customer));

    }

    private Accounts createNewAccount(Customer customer) {

        Accounts account = new Accounts();

        Long accountNumber = 100000000L + new Random().nextInt(900000000);

        account.setAccountNumber(accountNumber);
        account.setCustomerID(customer.getCustomerID());
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);
        account.setCreatedBy("Anonymous");
        account.setCreatedAt(LocalDateTime.now());

        return account;
    }
}
