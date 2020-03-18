package com.plm.platform.common.selector;

import com.plm.platform.common.configure.PlatformAuthExceptionConfigure;
import com.plm.platform.common.configure.PlatformOauth2FeignConfigure;
import com.plm.platform.common.configure.PlatformServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.Nonnull;

/**
 * cloud多注解结合
 */
public class PlatformCloudApplicationSelector implements ImportSelector {

    @Override
    @SuppressWarnings("all")
    public String[] selectImports(@Nonnull AnnotationMetadata annotationMetadata) {
        return new String[]{
                PlatformAuthExceptionConfigure.class.getName(),
                PlatformOauth2FeignConfigure.class.getName(),
                PlatformServerProtectConfigure.class.getName()
        };
    }
}
