package com.plm.platform.common.interceptor;

import com.plm.platform.common.entity.PlatformResponse;
import com.plm.platform.common.entity.constant.PlatformConstant;
import com.plm.platform.common.utils.PlatformUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微服务防护请求拦截器
 */
public class PlatformServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 从请求头中获取 Gateway Token
        String token = request.getHeader(PlatformConstant.GATEWAY_TOKEN_HEADER);
        String gatewayToken = new String(Base64Utils.encode(PlatformConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        // 校验 Gateway Token的正确性
        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            PlatformResponse platformResponse = new PlatformResponse();
            PlatformUtil.makeResponse(response,MediaType.APPLICATION_JSON_VALUE,
                    HttpServletResponse.SC_FORBIDDEN, platformResponse.message("请通过网关获取资源"));
            return false;
        }
    }
}
