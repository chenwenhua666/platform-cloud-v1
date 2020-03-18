package com.plm.platform.gateway.enhance.configure;

import com.plm.platform.common.annotation.EnablePlatformLettuceRedis;
import com.plm.platform.common.entity.constant.PlatformConstant;
import com.plm.platform.gateway.enhance.runner.PlatformRouteEnhanceRunner;
import com.plm.platform.gateway.enhance.service.BlackListService;
import com.plm.platform.gateway.enhance.service.RateLimitRuleService;
import com.plm.platform.gateway.enhance.service.RouteEnhanceCacheService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 */
@EnableAsync
@Configuration
@EnablePlatformLettuceRedis
@EnableReactiveMongoRepositories(basePackages = "com.plm.platform.gateway.enhance.mapper")
@ConditionalOnProperty(name = "platform.gateway.enhance", havingValue = "true")
public class PlatformRouteEnhanceConfigure {

    @Bean(PlatformConstant.ASYNC_POOL)
    public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("Platform-Gateway-Async-Thread");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean
    public ApplicationRunner platformRoutenEhanceRunner(RouteEnhanceCacheService cacheService,
                                                    BlackListService blackListService,
                                                    RateLimitRuleService rateLimitRuleService) {
        return new PlatformRouteEnhanceRunner(cacheService, blackListService, rateLimitRuleService);
    }
}
