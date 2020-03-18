package com.plm.platform.common.annotation;

import com.plm.platform.common.configure.PlatformOauth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PlatformOauth2FeignConfigure.class)
public @interface EnablePlatformOauth2FeignClient {
}
