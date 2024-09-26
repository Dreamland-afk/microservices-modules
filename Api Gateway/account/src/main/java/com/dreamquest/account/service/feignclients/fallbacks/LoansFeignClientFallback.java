package com.dreamquest.account.service.feignclients.fallbacks;

import com.dreamquest.account.dto.LoansDto;
import com.dreamquest.account.service.feignclients.LoansFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFeignClientFallback implements LoansFeignClients {
    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String mobileNumber, String correlationHeader) {
        return null;
    }
}
