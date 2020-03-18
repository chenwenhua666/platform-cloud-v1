package com.plm.platform.common.configure;

import com.plm.platform.common.handler.PlatformAccessDeniedHandler;
import com.plm.platform.common.handler.PlatformAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 异常翻译配置
 */
public class PlatformAuthExceptionConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public PlatformAccessDeniedHandler accessDeniedHandler() {
        return new PlatformAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public PlatformAuthExceptionEntryPoint authenticationEntryPoint() {
        return new PlatformAuthExceptionEntryPoint();
    }
}
