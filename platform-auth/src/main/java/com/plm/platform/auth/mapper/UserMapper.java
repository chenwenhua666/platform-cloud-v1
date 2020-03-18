package com.plm.platform.auth.mapper;

import com.plm.platform.common.entity.system.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *
 */
public interface UserMapper extends BaseMapper<SystemUser> {

    /**
     * 获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    SystemUser findByName(String username);
}
