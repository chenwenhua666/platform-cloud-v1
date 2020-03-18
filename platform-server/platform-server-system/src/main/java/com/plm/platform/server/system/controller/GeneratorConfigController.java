package com.plm.platform.server.system.controller;

import com.plm.platform.common.annotation.ControllerEndpoint;
import com.plm.platform.common.entity.PlatformResponse;
import com.plm.platform.common.entity.system.GeneratorConfig;
import com.plm.platform.common.exception.PlatformException;
import com.plm.platform.server.system.service.IGeneratorConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("generatorConfig")
public class GeneratorConfigController {

    private final IGeneratorConfigService generatorConfigService;

    @GetMapping
    @PreAuthorize("hasAuthority('gen:config')")
    public PlatformResponse getGeneratorConfig() {
        return new PlatformResponse().data(generatorConfigService.findGeneratorConfig());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('gen:config:update')")
    @ControllerEndpoint(operation = "修改生成代码配置", exceptionMessage = "修改GeneratorConfig失败")
    public void updateGeneratorConfig(@Valid GeneratorConfig generatorConfig) throws PlatformException {
        if (StringUtils.isBlank(generatorConfig.getId())) {
            throw new PlatformException("配置id不能为空");
        }
        this.generatorConfigService.updateGeneratorConfig(generatorConfig);
    }
}
