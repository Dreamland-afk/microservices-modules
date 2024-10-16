package com.dreamquest.apigateway.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

public class FilterUtility {

    public static final String CORRELATION_ID = "dreambank-correlation-id";;

    public static String getCorrelationId(HttpHeaders headers) {

        if (headers.get(CORRELATION_ID) != null) {
            List<String> requestHeaderList = headers.get(CORRELATION_ID);
            return requestHeaderList.stream().findFirst().get();
        } else {
            return null;
        }
    }


    public static ServerWebExchange setCorrelationId(String correlationId, ServerWebExchange exchange) {

       return  exchange.mutate().request(exchange.getRequest().mutate().header(CORRELATION_ID,correlationId).build()).build();
    }
}
