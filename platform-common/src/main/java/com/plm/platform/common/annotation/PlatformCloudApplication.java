package com.plm.platform.common.annotation;

import com.plm.platform.common.selector.PlatformCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 多注解结合为一个注解
 * EnablePlatformServerProtect，开启微服务防护，避免客户端绕过网关直接请求微服务；
 * EnablePlatformOauth2FeignClient，开启带令牌的Feign请求，避免微服务内部调用出现401异常；
 * EnablePlatformAuthExceptionHandler，认证类型异常翻译。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PlatformCloudApplicationSelector.class)
public @interface PlatformCloudApplication {
}
