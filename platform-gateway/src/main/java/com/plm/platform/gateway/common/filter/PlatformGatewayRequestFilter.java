package com.plm.platform.gateway.common.filter;

import com.plm.platform.common.entity.constant.PlatformConstant;
import com.plm.platform.gateway.common.properties.PlatformGatewayProperties;
import com.plm.platform.gateway.enhance.service.RouteEnhanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 */
@Slf4j
@Component
@Order(0)
@RequiredArgsConstructor
public class PlatformGatewayRequestFilter implements GlobalFilter {

    private final PlatformGatewayProperties properties;
    private final RouteEnhanceService routeEnhanceService;

    @Value("${platform.gateway.enhance:false}")
    private Boolean routeEhance;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (routeEhance) {
            Mono<Void> balckListResult = routeEnhanceService.filterBalckList(exchange);
            if (balckListResult != null) {
                routeEnhanceService.saveBlockLogs(exchange);
                return balckListResult;
            }
            Mono<Void> rateLimitResult = routeEnhanceService.filterRateLimit(exchange);
            if (rateLimitResult != null) {
                routeEnhanceService.saveRateLimitLogs(exchange);
                return rateLimitResult;
            }
            routeEnhanceService.saveRequestLogs(exchange);
        }

        byte[] token = Base64Utils.encode((PlatformConstant.GATEWAY_TOKEN_VALUE).getBytes());
        String[] headerValues = {new String(token)};
        ServerHttpRequest build = exchange.getRequest().mutate().header(PlatformConstant.GATEWAY_TOKEN_HEADER, headerValues).build();
        ServerWebExchange newExchange = exchange.mutate().request(build).build();
        return chain.filter(newExchange);
    }
}
