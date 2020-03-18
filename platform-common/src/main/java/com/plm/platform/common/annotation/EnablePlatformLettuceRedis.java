package com.plm.platform.common.annotation;

import com.plm.platform.common.configure.PlatformLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PlatformLettuceRedisConfigure.class)
public @interface EnablePlatformLettuceRedis {
}
