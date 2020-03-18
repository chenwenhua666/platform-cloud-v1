package com.plm.platform.server.foundation;

import com.plm.platform.common.annotation.EnablePlatformLettuceRedis;
import com.plm.platform.common.annotation.PlatformCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@SpringBootApplication
@EnablePlatformLettuceRedis
@PlatformCloudApplication
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("com.plm.platform.server.foundation.mapper")
public class PlatformServerFoundationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformServerFoundationApplication.class, args);
	}

}
