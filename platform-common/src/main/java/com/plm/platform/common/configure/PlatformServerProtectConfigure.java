package com.plm.platform.common.configure;

import com.plm.platform.common.interceptor.PlatformServerProtectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 微服务防护配置
 */
public class PlatformServerProtectConfigure implements WebMvcConfigurer {

    private HandlerInterceptor platformServerProtectInterceptor;

    @Autowired
    public void setPlatformServerProtectInterceptor(HandlerInterceptor platformServerProtectInterceptor) {
        this.platformServerProtectInterceptor = platformServerProtectInterceptor;
    }

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HandlerInterceptor platformServerProtectInterceptor() {
        return new PlatformServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(platformServerProtectInterceptor);
    }
}
