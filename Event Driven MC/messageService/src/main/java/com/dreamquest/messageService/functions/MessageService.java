package com.dreamquest.messageService.functions;

import com.dreamquest.messageService.dto.AccountMsgDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class MessageService {

//    @Bean
//    public Supplier<Mono<Void>> sms() {
//        return () -> {
//            System.out.println("SMS sent successfully");
//            return Mono.empty();  // Return an empty Mono<Void> instead of null
//        };
//    }

//    @Bean
//    public Supplier<Mono<Void>> multisms() {
//        return () -> {
//            System.out.println("SMS sent successfully from second sms");
//            return Mono.empty();  // Return an empty Mono<Void> instead of null
//        };
//    }


    @Bean
    public Function<AccountMsgDto,AccountMsgDto> email()
    {

       return  accountMsgDto -> {

           System.out.println("Sending email to: " + accountMsgDto.email());
           return  accountMsgDto;
       };

    }

    @Bean
    public Function<AccountMsgDto,String> sms()
    {

        return  accountMsgDto -> {

            System.out.println("Sending SMS to: " + accountMsgDto.mobileNumber());
            return  "Sms notification has been sent to the registered mobile number: " + accountMsgDto.mobileNumber();
        };

    }

//    @Bean
//    public Function<AccountMsgDto,String> abc()
//    {
//
//        return  accountMsgDto -> {
//
//            System.out.println("Sending email to: " + accountMsgDto.email());
//            return  accountMsgDto.email();
//        };
//
//    }

}

