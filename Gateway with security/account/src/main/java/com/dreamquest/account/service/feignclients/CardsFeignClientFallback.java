package com.dreamquest.account.service.feignclients;

import com.dreamquest.account.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFeignClientFallback implements CardsFeignClient {

    @Override
    public ResponseEntity<CardsDto> fetchCard(String mobileNumber, String correlationHeader) {
        return null;
    }
}
