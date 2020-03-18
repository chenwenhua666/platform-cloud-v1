package com.plm.platform.server.foundation.service.fallback;

import com.plm.platform.common.annotation.Fallback;
import com.plm.platform.server.foundation.service.IUserService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * Feign回退
 */
@Slf4j
@Fallback
public class UserServiceFallback implements FallbackFactory<IUserService> {

    @Override
    public IUserService create(Throwable throwable) {
        return (p, u) -> {
            log.error("获取用户信息失败", throwable);
            return null;
        };
        /*return new IUserService() {
            @Override
            public PlatformResponse userList(QueryRequest queryRequest, SystemUser user) {
                return null;
            }
        };*/
    }
}