package com.dreamquest.account.service.feignclients.fallbacks;

import com.dreamquest.account.dto.CardsDto;
import com.dreamquest.account.service.feignclients.CardsFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFeignClientFallback implements CardsFeignClient {

    @Override
    public ResponseEntity<CardsDto> fetchCard(String mobileNumber, String correlationHeader) {
        return null;
    }
}
