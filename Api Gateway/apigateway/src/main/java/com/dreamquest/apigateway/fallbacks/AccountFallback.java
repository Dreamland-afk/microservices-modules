package com.dreamquest.apigateway.fallbacks;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AccountFallback {

    @RequestMapping("/fallback")
    public Mono<String> accountFallback( Throwable throwable) throws Throwable {

        throw throwable;

//        System.out.println(throwable.getLocalizedMessage());
//        return Mono.just("Circuit break executed. Please give it a try after some time." + throwable.getMessage());
    }
}
