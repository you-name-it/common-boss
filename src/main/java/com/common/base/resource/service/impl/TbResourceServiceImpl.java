package com.common.base.resource.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.base.resource.entity.TbResource;
import com.common.base.resource.mapper.TbResourceMapper;
import com.common.base.resource.service.ITbResourceService;
import com.common.base.resourcerole.mapper.TbResourcesRoleMapper;
import com.common.utils.TreeUtil;
import com.feilong.core.bean.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class TbResourceServiceImpl extends ServiceImpl<TbResourceMapper, TbResource> implements ITbResourceService {

    @Autowired
    private TbResourceMapper resourceMapper;

    @Autowired
    private TbResourcesRoleMapper resourceRoleMapper;

    @Override
    public List<TbResource> findResourcesByUserId(int userId) {
        return resourceMapper.findResourcesByUserId(userId);
    }

    @Override
    public List<TbResource> findResourcesMenuByUserId(int userId) {
        List<TbResource> resources = resourceMapper.findResourcesMenuByUserId(userId);
        List<TbResource> treeMenuList =new TreeUtil().treeMenuList(resources, null);
        return treeMenuList;
    }

    @Override
    public List<TbResource> findAllResourcesMenu() {
        List<TbResource> resources = resourceMapper.findAllResourcesMenu();
        List<TbResource> treeMenuList =new TreeUtil().treeMenuList(resources, null);
        return treeMenuList;
    }

    @Override
    public List<TbResource> queryResourceList(Map<String, Object> parameter) {
        return resourceMapper.queryResourceList(parameter);
    }

    @Override
    public Page<TbResource> selectResourceList(Page<TbResource> page, Map<String, Object> parameter) {
        page.setRecords(resourceMapper.selectResourceList(page,parameter));
        return page;
    }

    @Override
    @Transactional
    public void deleteRoleResource(int resourceId) {
        resourceRoleMapper.deleteByMap(ConvertUtil.toMap("s_id",(Object)resourceId));
        resourceMapper.deleteById(resourceId);

    }
}
