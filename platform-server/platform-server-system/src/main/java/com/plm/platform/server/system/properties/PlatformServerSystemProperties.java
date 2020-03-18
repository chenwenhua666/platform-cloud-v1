package com.plm.platform.server.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 *
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:platform-server-system.properties"})
@ConfigurationProperties(prefix = "platform.server.system")
public class PlatformServerSystemProperties {
    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;
    /**
     * 批量插入当批次可插入的最大值
     */
    private Integer batchInsertMaxNum = 1000;
    /**
     * swagger配置
     */
    private PlatformSwaggerProperties swagger = new PlatformSwaggerProperties();
}
