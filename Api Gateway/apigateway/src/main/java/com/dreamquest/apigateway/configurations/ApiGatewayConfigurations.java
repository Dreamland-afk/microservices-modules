package com.dreamquest.apigateway.configurations;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import static org.springframework.cloud.gateway.support.RouteMetadataUtils.CONNECT_TIMEOUT_ATTR;
import static org.springframework.cloud.gateway.support.RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR;

@Configuration
public class ApiGatewayConfigurations {

    String timeHeader = "X-Response-Time";


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/dreambank/accounts/**")
                        .filters(f -> f.rewritePath("/dreambank/accounts/?(?<remaining>.*)", "/${remaining}")
                                .addResponseHeader(timeHeader, String.valueOf(System.currentTimeMillis()))
                                .circuitBreaker(cb -> cb.setName("accountCircuitBreaker").setFallbackUri("forward:/fallback")).metadata(RESPONSE_TIMEOUT_ATTR, 10000))
                        .uri("lb://ACCOUNTS")
                        )
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
