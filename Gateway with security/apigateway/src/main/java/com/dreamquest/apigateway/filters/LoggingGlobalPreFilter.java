package com.dreamquest.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
public class LoggingGlobalPreFilter implements GlobalFilter {

    final Logger logger =
            LoggerFactory.getLogger(LoggingGlobalPreFilter.class);

    /**
     * Process the Web request and (optionally) delegate to the next {@code GatewayFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        HttpHeaders headers = exchange.getRequest().getHeaders();

        if(isCorrelationIdPresent(headers))
        {

            logger.debug("dreambank-correlation-id found in RequestTraceFilter : {}",
                    FilterUtility.getCorrelationId(headers));
        }
        else {

            System.out.println("Generating Correlation Id");
            String correlationId = generateCorrelationId();

            exchange = FilterUtility.setCorrelationId(correlationId, exchange);

            logger.debug("eazyBank-correlation-id generated in RequestTraceFilter : {}", correlationId);

            System.out.println(exchange.getRequest().getHeaders());

        }

        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders headers) {

        return FilterUtility.getCorrelationId(headers) != null;

    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}
