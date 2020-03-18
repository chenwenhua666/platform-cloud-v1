package com.plm.platform.server.foundation.controller;

import com.plm.platform.common.entity.PlatformResponse;
import com.plm.platform.common.entity.QueryRequest;
import com.plm.platform.common.entity.system.SystemUser;
import com.plm.platform.common.utils.PlatformUtil;
import com.plm.platform.server.foundation.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cwh
 */
@Slf4j
@Validated
@RestController
@RequestMapping
@RequiredArgsConstructor
public class PlatformServerFoundationController {

    private final IUserService userService;

    /**
     * Feign调用platform-system-server远程方法
     */
    @GetMapping("user/list")
    public PlatformResponse getRemoteUserList(QueryRequest request, SystemUser user) {
        return userService.userList(request, user);
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/user")
    public Map<String, Object> currentUser() {
        Map<String, Object> map = new HashMap<>(5);
        map.put("currentUser", PlatformUtil.getCurrentUser());
        map.put("currentUsername", PlatformUtil.getCurrentUsername());
        map.put("currentUserAuthority", PlatformUtil.getCurrentUserAuthority());
        map.put("currentTokenValue", PlatformUtil.getCurrentTokenValue());
        map.put("currentRequestIpAddress", PlatformUtil.getHttpServletRequestIpAddress());
        return map;
    }

}
