package ${basePackage}.${controllerPackage};

import com.plm.platform.common.entity.PlatformResponse;
import com.plm.platform.common.exception.PlatformException;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.I${className}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * ${tableComment} Controller
 *
 * @author ${author}
 * @date ${date}
 */
@Slf4j
@Validated
@RestController
@RequestMapping("${className?uncap_first}")
public class ${className}Controller extends BaseController {

    @Autowired
    private I${className}Service ${className?uncap_first}Service;

    @GetMapping
    @PreAuthorize("hasAuthority('${className?uncap_first}:list')")
    public PlatformResponse getAll${className}s(${className} ${className?uncap_first}) {
        return new PlatformResponse().data(${className?uncap_first}Service.find${className}s(${className?uncap_first}));
    }

    @GetMapping("list")
    @PreAuthorize("hasAuthority('${className?uncap_first}:list')")
    public PlatformResponse ${className?uncap_first}List(QueryRequest request, ${className} ${className?uncap_first}) {
        Map
<String, Object> dataTable = getDataTable(this.${className?uncap_first}Service.find${className}s(request, ${className?uncap_first}));
        return new PlatformResponse().data(dataTable);
    }

    @Log("新增${className}")
    @PostMapping
    @PreAuthorize("hasAuthority('${className?uncap_first}:add')")
    public void add${className}(@Valid ${className} ${className?uncap_first}) throws PlatformException {
        try {
            this.${className?uncap_first}Service.create${className}(${className?uncap_first});
        } catch (Exception e) {
            String message = "新增${className}失败";
            log.error(message, e);
            throw new PlatformException(message);
        }
    }

    @Log("删除${className}")
    @DeleteMapping
    @PreAuthorize("hasAuthority('${className?uncap_first}:delete')")
    public void delete${className}(${className} ${className?uncap_first}) throws PlatformException {
        try {
            this.${className?uncap_first}Service.delete${className}(${className?uncap_first});
        } catch (Exception e) {
            String message = "删除${className}失败";
            log.error(message, e);
            throw new PlatformException(message);
        }
    }

    @Log("修改${className}")
    @PutMapping
    @PreAuthorize("hasAuthority('${className?uncap_first}:update')")
    public void update${className}(${className} ${className?uncap_first}) throws PlatformException {
        try {
            this.${className?uncap_first}Service.update${className}(${className?uncap_first});
        } catch (Exception e) {
            String message = "修改${className}失败";
            log.error(message, e);
            throw new PlatformException(message);
        }
    }
}
