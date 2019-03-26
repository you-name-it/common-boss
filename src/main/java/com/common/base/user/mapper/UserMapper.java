package com.common.base.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.common.base.user.entity.User;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    User selectUserRole(Map<String, Object> parameter);

}
