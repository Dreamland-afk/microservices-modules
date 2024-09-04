package com.dreamquest.loans.service.impl;

import com.dreamquest.loans.Constants.LoansConstants;
import com.dreamquest.loans.dto.LoansDto;
import com.dreamquest.loans.entity.Loans;
import com.dreamquest.loans.exception.LoansAlreadyExistsException;
import com.dreamquest.loans.exception.ResourceNotFoundException;
import com.dreamquest.loans.mapper.LoansMapper;
import com.dreamquest.loans.repository.LoansRepository;
import com.dreamquest.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    LoansRepository loansRepository;


    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);

        if (optionalLoans.isPresent())
            throw new LoansAlreadyExistsException("Loan already registered with given mobileNumber " + mobileNumber);

        loansRepository.save(createNewLoan(mobileNumber));

    }

    private Loans createNewLoan(String mobileNumber) {

        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {

        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(

                () -> new ResourceNotFoundException("Loans", "mobile Number", mobileNumber)
        );

        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    /**
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {

        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "loan Number", loansDto.getLoanNumber())
        );

        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {

        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(

                () -> new ResourceNotFoundException("Loans", "mobile Number", mobileNumber)
        );

        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}
