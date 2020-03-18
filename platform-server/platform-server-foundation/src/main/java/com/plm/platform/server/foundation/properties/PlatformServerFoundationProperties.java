package com.plm.platform.server.foundation.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author cwh
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:platform-server-foundation.properties"})
@ConfigurationProperties(prefix = "platform-server-foundation")
public class PlatformServerFoundationProperties {
    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;
}
