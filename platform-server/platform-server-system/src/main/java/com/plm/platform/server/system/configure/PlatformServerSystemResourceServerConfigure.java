package com.plm.platform.server.system.configure;

import com.plm.platform.common.entity.constant.EndpointConstant;
import com.plm.platform.common.handler.PlatformAccessDeniedHandler;
import com.plm.platform.common.handler.PlatformAuthExceptionEntryPoint;
import com.plm.platform.server.system.properties.PlatformServerSystemProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class PlatformServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {

    private final PlatformAccessDeniedHandler accessDeniedHandler;
    private final PlatformAuthExceptionEntryPoint exceptionEntryPoint;
    private final PlatformServerSystemProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.headers().frameOptions().disable()
                .and().csrf().disable()
                .requestMatchers().antMatchers(EndpointConstant.ALL)
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers(EndpointConstant.ALL).authenticated()
                .and().httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
