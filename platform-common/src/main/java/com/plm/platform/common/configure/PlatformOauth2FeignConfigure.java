package com.plm.platform.common.configure;

import com.plm.platform.common.entity.constant.PlatformConstant;
import com.plm.platform.common.utils.PlatformUtil;
import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Base64Utils;

/**
 * OAuth2 Feign配置
 */
public class PlatformOauth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            // 请求头中添加 Gateway Token
            String gatewayToken = new String(Base64Utils.encode(PlatformConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(PlatformConstant.GATEWAY_TOKEN_HEADER, gatewayToken);
            // 请求头中添加原请求头中的 Token
            String authorizationToken = PlatformUtil.getCurrentTokenValue();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, PlatformConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
        };
    }
}
