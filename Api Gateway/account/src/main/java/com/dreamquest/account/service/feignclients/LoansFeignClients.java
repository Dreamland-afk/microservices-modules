package com.dreamquest.account.service.feignclients;

import com.dreamquest.account.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans")
public interface LoansFeignClients {

    @GetMapping("/api/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber, String correlationHeader);
}
