package com.plm.platform.common.annotation;

import com.plm.platform.common.configure.PlatformServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PlatformServerProtectConfigure.class)
public @interface EnablePlatformServerProtect {
}
