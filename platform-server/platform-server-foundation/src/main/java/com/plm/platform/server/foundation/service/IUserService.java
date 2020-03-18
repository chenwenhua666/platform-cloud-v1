package com.plm.platform.server.foundation.service;

import com.plm.platform.common.entity.PlatformResponse;
import com.plm.platform.common.entity.QueryRequest;
import com.plm.platform.common.entity.constant.PlatformServerConstant;
import com.plm.platform.common.entity.system.SystemUser;
import com.plm.platform.server.foundation.service.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Feign客户端
 *
 * @author cwh
 */
@FeignClient(value = PlatformServerConstant.PLATFORM_SERVER_SYSTEM, contextId = "userServiceClient", fallbackFactory = UserServiceFallback.class)
public interface IUserService {

    /**
     * remote /user endpoint
     *
     * @param queryRequest queryRequest
     * @param user         user
     * @return PlatformResponse
     */
    @GetMapping("user")
    PlatformResponse userList(@RequestParam("queryRequest") QueryRequest queryRequest, @RequestParam("user") SystemUser user);

}
