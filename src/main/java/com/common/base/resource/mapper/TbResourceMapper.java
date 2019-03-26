package com.common.base.resource.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.common.base.resource.entity.TbResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface TbResourceMapper extends BaseMapper<TbResource> {

    public List<TbResource> findResourcesByUserId(@Param(value="userId") int userId);

    public List<TbResource> findResourcesMenuByUserId(@Param(value="userId") int userId);

    public List<TbResource> findAllResourcesMenu();

    public List<TbResource> queryResourceList(Map<String, Object> parameter);

    public List<TbResource> selectResourceList(Pagination page, Map<String, Object> parameter);
}
