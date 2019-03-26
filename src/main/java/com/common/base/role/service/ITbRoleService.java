package com.common.base.role.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.base.role.entity.TbRole;

import java.util.List;

/**
 *
 */
public interface ITbRoleService extends IService<TbRole> {

    public void savePermission(Integer roleId,List<Integer> resourceIds);

    public boolean deleteRoleResource(Integer roleId);
}