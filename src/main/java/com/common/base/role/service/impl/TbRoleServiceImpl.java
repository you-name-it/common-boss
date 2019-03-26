package com.common.base.role.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.base.resourcerole.entity.TbResourcesRole;
import com.common.base.resourcerole.mapper.TbResourcesRoleMapper;
import com.common.base.role.entity.TbRole;
import com.common.base.role.mapper.TbRoleMapper;
import com.common.base.role.service.ITbRoleService;
import com.feilong.core.bean.ConvertUtil;
import com.feilong.core.util.CollectionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色表  服务实现类
 * </p>
 *
 * @author laizhilong
 *
 * @since 2017-04-12
 */
@Service
public class TbRoleServiceImpl extends ServiceImpl<TbRoleMapper, TbRole> implements ITbRoleService {

    @Autowired
    private TbResourcesRoleMapper resourceRoleMapper;
    @Autowired
    private TbRoleMapper roleMapper;

    @Override
    @Transactional
    public void savePermission(Integer roleId, List<Integer> resourceIds) {
        List<TbResourcesRole> roleResources = resourceRoleMapper.selectByMap(ConvertUtil.toMap("r_id",(Object)roleId));
        List<TbResourcesRole> newRes = new ArrayList<>();
        for(Integer sid : resourceIds){
            TbResourcesRole rr = new TbResourcesRole();
            rr.setRid(roleId);
            rr.setSid(sid);
            newRes.add(rr);
        }

        //查找出需要删除的
        List<TbResourcesRole> removeRes = CollectionsUtil.selectRejected(roleResources,"sid",resourceIds);
        //查找需要新增的
        List<TbResourcesRole> addRes = CollectionsUtil.selectRejected(newRes,"sid",CollectionsUtil.getPropertyValueList(roleResources, "sid"));
        for(TbResourcesRole r:removeRes){
            resourceRoleMapper.deleteById(r.getId());
        }
        for(TbResourcesRole r:addRes){
            r.setCreateTime(new Date(System.currentTimeMillis()));
            resourceRoleMapper.insert(r);
        }
    }

    @Override
    @Transactional
    public boolean deleteRoleResource(Integer roleId) {
        resourceRoleMapper.deleteByMap(ConvertUtil.toMap("r_id",(Object)roleId));
        if(roleMapper.deleteById(roleId)>0){
            return true;
        }
        return false;
    }

}
