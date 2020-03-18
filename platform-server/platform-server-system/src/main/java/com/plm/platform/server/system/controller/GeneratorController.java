package com.plm.platform.server.system.controller;

import com.plm.platform.common.annotation.ControllerEndpoint;
import com.plm.platform.common.entity.PlatformResponse;
import com.plm.platform.common.entity.QueryRequest;
import com.plm.platform.common.entity.constant.GeneratorConstant;
import com.plm.platform.common.entity.system.Column;
import com.plm.platform.common.entity.system.GeneratorConfig;
import com.plm.platform.common.exception.PlatformException;
import com.plm.platform.common.utils.PlatformUtil;
import com.plm.platform.common.utils.FileUtil;
import com.plm.platform.server.system.helper.GeneratorHelper;
import com.plm.platform.server.system.service.IGeneratorConfigService;
import com.plm.platform.server.system.service.IGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("generator")
public class GeneratorController {

    private static final String SUFFIX = "_code.zip";

    private final IGeneratorService generatorService;
    private final IGeneratorConfigService generatorConfigService;
    private final GeneratorHelper generatorHelper;

    @GetMapping("tables")
    @PreAuthorize("hasAuthority('gen:generate')")
    public PlatformResponse tablesInfo(String tableName, QueryRequest request) {
        Map<String, Object> dataTable = PlatformUtil.getDataTable(generatorService.getTables(tableName, request, GeneratorConstant.DATABASE_TYPE, GeneratorConstant.DATABASE_NAME));
        return new PlatformResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('gen:generate:gen')")
    @ControllerEndpoint(operation = "生成代码", exceptionMessage = "代码生成失败")
    public void generate(@NotBlank(message = "{required}") String name, String remark, HttpServletResponse response) throws Exception {
        GeneratorConfig generatorConfig = generatorConfigService.findGeneratorConfig();
        if (generatorConfig == null) {
            throw new PlatformException("代码生成配置为空");
        }

        String className = name;
        if (GeneratorConfig.TRIM_YES.equals(generatorConfig.getIsTrim())) {
            className = RegExUtils.replaceFirst(name, generatorConfig.getTrimValue(), StringUtils.EMPTY);
        }

        generatorConfig.setTableName(name);
        generatorConfig.setClassName(PlatformUtil.underscoreToCamel(className));
        generatorConfig.setTableComment(remark);
        // 生成代码到临时目录
        List<Column> columns = generatorService.getColumns(GeneratorConstant.DATABASE_TYPE, GeneratorConstant.DATABASE_NAME, name);
        generatorHelper.generateEntityFile(columns, generatorConfig);
        generatorHelper.generateMapperFile(columns, generatorConfig);
        generatorHelper.generateMapperXmlFile(columns, generatorConfig);
        generatorHelper.generateServiceFile(columns, generatorConfig);
        generatorHelper.generateServiceImplFile(columns, generatorConfig);
        generatorHelper.generateControllerFile(columns, generatorConfig);
        // 打包
        String zipFile = System.currentTimeMillis() + SUFFIX;
        FileUtil.compress(GeneratorConstant.TEMP_PATH + "src", zipFile);
        // 下载
        FileUtil.download(zipFile, name + SUFFIX, true, response);
        // 删除临时目录
        FileUtil.delete(GeneratorConstant.TEMP_PATH);
    }
}
