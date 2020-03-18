package com.plm.platform.common.handler;

import com.plm.platform.common.entity.PlatformResponse;
import com.plm.platform.common.utils.PlatformUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Slf4j
public class PlatformAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        int status = HttpServletResponse.SC_UNAUTHORIZED;
        String message = "token不合法";
        if (StringUtils.containsIgnoreCase(message, "Invalid access token")) {
            message = "访问令牌不正确";
        }
        if (StringUtils.containsIgnoreCase(message, "Full authentication is required to access this resource")) {
            message = "请求头client信息不正确，烦请核对";
            status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }
        log.error(message, authException);
        PlatformUtil.makeResponse(response, MediaType.APPLICATION_JSON_VALUE, status, new PlatformResponse().message(message));
    }
}
