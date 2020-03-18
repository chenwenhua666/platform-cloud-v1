package com.plm.platform.gateway.enhance.runner;

import com.plm.platform.gateway.enhance.service.BlackListService;
import com.plm.platform.gateway.enhance.service.RateLimitRuleService;
import com.plm.platform.gateway.enhance.service.RouteEnhanceCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 *
 */
@Slf4j
@RequiredArgsConstructor
public class PlatformRouteEnhanceRunner implements ApplicationRunner {

    private final RouteEnhanceCacheService cacheService;
    private final BlackListService blackListService;
    private final RateLimitRuleService rateLimitRuleService;

    @Override
    public void run(ApplicationArguments args) {
        cacheService.saveAllBlackList(blackListService.findAll());
        cacheService.saveAllRateLimitRules(rateLimitRuleService.findAll());
    }
}
