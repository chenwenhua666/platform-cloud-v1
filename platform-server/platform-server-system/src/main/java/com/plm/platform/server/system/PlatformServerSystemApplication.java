package com.plm.platform.server.system;

import com.plm.platform.common.annotation.PlatformCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
// @EnablePlatformLettuceRedis
@PlatformCloudApplication
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("com.plm.platform.server.system.mapper")
public class PlatformServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformServerSystemApplication.class, args);
    }
}
