package com.common.base.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.base.user.entity.User;

import java.util.Map;

public interface UserService extends IService<User> {

    public User selectUserRole(Map<String, Object> parameter);

    public boolean insertAll(User user);

}
