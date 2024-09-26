package com.dreamquest.account.service.feignclients;


import com.dreamquest.account.dto.CardsDto;
import com.dreamquest.account.service.feignclients.fallbacks.CardsFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards", fallback = CardsFeignClientFallback.class)
public interface CardsFeignClient {

    @GetMapping("/api/fetch")
    ResponseEntity<CardsDto> fetchCard(@RequestParam String mobileNumber, @RequestHeader("dreambank-correlation-id") String correlationHeader);

}
