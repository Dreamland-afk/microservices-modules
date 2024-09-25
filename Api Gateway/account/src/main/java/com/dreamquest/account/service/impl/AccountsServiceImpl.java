package com.dreamquest.account.service.impl;

import com.dreamquest.account.Constants.AccountsConstants;
import com.dreamquest.account.dto.AccountsDto;
import com.dreamquest.account.dto.CustomerDto;
import com.dreamquest.account.entity.Accounts;
import com.dreamquest.account.entity.Customer;
import com.dreamquest.account.exception.CustomerAlreadyExistsException;
import com.dreamquest.account.exception.ResourceNotFoundException;
import com.dreamquest.account.mapper.AccountsMapper;
import com.dreamquest.account.mapper.CustomerMapper;
import com.dreamquest.account.repository.AccountsRepo;
import com.dreamquest.account.repository.CustomerRepo;
import com.dreamquest.account.service.IAccountsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

        if (isPersentMobileNumber.isPresent())
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number " + customerDto.getMobileNumber());
        customer.setCreatedBy("Anonymous");
        customer = customerRepo.save(customer);
        accountsRepo.save(createNewAccount(customer));

    }

    private Accounts createNewAccount(Customer customer) {

        Accounts account = new Accounts();

        Long accountNumber = 1000000000L + new Random().nextInt(900000000);

        account.setAccountNumber(accountNumber);
        account.setCustomerID(customer.getCustomerID());
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);
        account.setCreatedBy("Anonymous");


        return account;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );

        Accounts accounts = accountsRepo.findByCustomerID(customer.getCustomerID()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerID().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());

        customerDto.setAccountsDto(accountsDto);
        return customerDto;
    }


    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts optionalAccounts = accountsRepo.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Accounts", "Account Number", accountsDto.getAccountNumber().toString())
            );
            Customer optionalCustomer = customerRepo.findById(optionalAccounts.getCustomerID()).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "Customer ID", optionalAccounts.getCustomerID().toString())
            );


            Accounts accounts = AccountsMapper.maptoAccounts(accountsDto, optionalAccounts);
            Customer customer = CustomerMapper.mapToCustomer(customerDto, optionalCustomer);

            accountsRepo.save(accounts);
            customerRepo.save(customer);

            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    @Transactional
    public boolean deleteAccount(String mobileNumber) {

        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Customer ID", mobileNumber)
        );

        accountsRepo.deleteByCustomerID(customer.getCustomerID());
        customerRepo.delete(customer);


        return true;
    }
}
