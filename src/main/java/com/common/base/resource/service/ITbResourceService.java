package com.common.base.resource.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.common.base.resource.entity.TbResource;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface ITbResourceService extends IService<TbResource> {


    public List<TbResource> findResourcesByUserId(int userId);

    public List<TbResource> findResourcesMenuByUserId(int userId);

    public List<TbResource> findAllResourcesMenu();

    public List<TbResource> queryResourceList(Map<String, Object> parameter);

    public Page<TbResource> selectResourceList(Page<TbResource> page, Map<String, Object> parameter);

    public void deleteRoleResource(int resourceId);
}
