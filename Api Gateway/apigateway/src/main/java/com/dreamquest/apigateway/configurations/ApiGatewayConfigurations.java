package com.dreamquest.apigateway.configurations;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Configuration
public class ApiGatewayConfigurations {

    String timeHeader = "X-Response-Time";


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/dreambank/accounts/**")
                        .filters(f -> f.rewritePath("/dreambank/accounts/?(?<remaining>.*)", "/${remaining}")
                                .addResponseHeader(timeHeader, String.valueOf(System.currentTimeMillis()))
                                .circuitBreaker(cb -> cb.setName("accountCircuitBreaker").setFallbackUri("/fallback")))
                        .uri("lb://ACCOUNTS"))
                .route(r -> r.path("/dreambank/cards/**")
                        .filters(f -> f.rewritePath("/dreambank/cards/?(?<remaining>.*)", "/${remaining}")
                                .addResponseHeader(timeHeader, String.valueOf(System.currentTimeMillis())))
                        .uri("lb://CARDS"))
                .route(r -> r.path("/dreambank/loans/**")
                        .filters(f -> f.rewritePath("/dreambank/loans/?(?<remaining>.*)", "/${remaining}")
                                .addResponseHeader(timeHeader, String.valueOf(System.currentTimeMillis())))
                        .uri("lb://LOANS"))
                .build();

    }

}
