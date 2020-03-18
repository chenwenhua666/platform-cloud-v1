package com.plm.platform.auth;

import com.plm.platform.common.annotation.EnablePlatformAuthExceptionHandler;
import com.plm.platform.common.annotation.EnablePlatformLettuceRedis;
import com.plm.platform.common.annotation.EnablePlatformServerProtect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 认证服务启动入口
 */
@EnableDiscoveryClient
@EnablePlatformLettuceRedis
@EnablePlatformAuthExceptionHandler
@EnablePlatformServerProtect
@SpringBootApplication
@MapperScan("com.plm.platform.auth.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PlatformAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformAuthApplication.class, args);
    }
}
