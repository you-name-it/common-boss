package com.common.base.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.base.user.entity.User;
import com.common.base.user.mapper.UserMapper;
import com.common.base.user.service.UserService;
import com.common.base.userrole.entity.TbRoleUser;
import com.common.base.userrole.mapper.TbRoleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * 〈用户业务类〉
 *
 * @author HuangQiuRong
 * @create 2019/1/3
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TbRoleUserMapper roleUserMapper;

    @Override
    public User selectUserRole(Map<String, Object> parameter) {
        return userMapper.selectUserRole(parameter);
    }

    @Override
    @Transactional
    public boolean insertAll(User user) {
        if(userMapper.insert(user)>0){
            TbRoleUser roleUser = new TbRoleUser();
            roleUser.setRId(1);
            roleUser.setUId(user.getID());
            roleUser.setTCreateTime(new Date(System.currentTimeMillis()));
            if(roleUserMapper.insert(roleUser)>0){
                return true;
            }
        }
        return false;
    }
}
