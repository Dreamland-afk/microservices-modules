package com.dreamquest.account.service.feignclients;

import com.dreamquest.account.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFeignClientFallback implements LoansFeignClients {
    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String mobileNumber, String correlationHeader) {
        return null;
    }
}
