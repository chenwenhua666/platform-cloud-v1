package com.plm.platform.gateway.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 *
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:platform-gateway.properties"})
@ConfigurationProperties(prefix = "platform.gateway")
public class PlatformGatewayProperties {
    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;
}
