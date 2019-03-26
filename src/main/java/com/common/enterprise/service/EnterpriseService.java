package com.common.enterprise.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.base.user.entity.User;
import com.common.enterprise.entity.Company;

/**
 * @author HuangQiuRong
 * @create 2019/1/11
 */

public interface EnterpriseService extends IService<Company> {
    Boolean insertAndUpdateUser(Company company, User sessionUser);
}
