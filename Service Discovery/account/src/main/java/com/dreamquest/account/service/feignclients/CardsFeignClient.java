package com.dreamquest.account.service.feignclients;


import com.dreamquest.account.dto.CardsDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards")
public interface CardsFeignClient {

    @GetMapping("/api/fetch")
    ResponseEntity<CardsDto> fetchCard(@RequestParam String mobileNumber);

}
