package com.common.base.resource.web;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.common.bean.AbstractBean;
import com.common.base.common.exception.EnumSvrResult;
import com.common.base.common.model.Select2Entity;
import com.common.base.index.web.BaseController;
import com.common.base.resource.entity.TbResource;
import com.common.base.resource.service.ITbResourceService;
import com.common.utils.JsonUtil;
import com.common.utils.TreeUtil;
import com.common.utils.dtgrid.model.Pager;
import com.feilong.core.Validator;
import com.feilong.core.bean.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/resource/")
public class TbResourceController extends BaseController {

    @Autowired
    private ITbResourceService resourceService;

    @GetMapping("listUI")
    public String listUI() {

        return "resource/list";
    }

    @PostMapping("list")
    @ResponseBody
    public Object list(String gridPager) {
        Pager pager = JsonUtil.getObjectFromJson(gridPager, Pager.class);
        Map<String, Object> parameters = null;
        if(Validator.isNullOrEmpty(pager.getParameters())){
            parameters = new HashMap<>();
        }else{
            parameters = pager.getParameters();
        }
        Page<TbResource> list = resourceService.selectPage(new Page<TbResource>(pager.getNowPage(), pager.getPageSize()), Condition.instance().allEq(parameters));
        parameters.clear();
        parameters.put("isSuccess", Boolean.TRUE);
        parameters.put("nowPage", pager.getNowPage());
        parameters.put("pageSize",pager.getPageSize());
        parameters.put("pageCount", list.getPages());
        parameters.put("recordCount", list.getTotal());
        parameters.put("startRecord", list.getOffsetCurrent());
        parameters.put("exhibitDatas",list.getRecords());
        return parameters;
    }

    @GetMapping("form")
    public String form(Map<String,Object> map) {
        List<TbResource> list = resourceService.selectByMap(null);
        List<Select2Entity> select2List = new TreeUtil().getSelectTree(list, null);
        map.put("resources", select2List);
        return "resource/form";
    }

    @PostMapping("save")
    @ResponseBody
    public AbstractBean add(TbResource resource){
        resource.setParentId(resource.getParentId()==0?null:resource.getParentId());
        if(resource.getId()==null){
            resource.setCreateTime(new Date(System.currentTimeMillis()));
            resource.setUpdateTime(new Date(System.currentTimeMillis()));
        }else
        {
            resource.setUpdateTime(new Date(System.currentTimeMillis()));
        }

        if(!resourceService.insertOrUpdate(resource)){
            return fail(EnumSvrResult.ERROR);
        }
        return success();
    }

    @DeleteMapping(value="{id}/delete")
    @ResponseBody
    public AbstractBean delete(@PathVariable(required=true) Integer id) {
        List<TbResource> childrens = resourceService.selectByMap(ConvertUtil.toMap("s_parent_id",(Object)id));
        if(childrens!=null && childrens.size()>0){
            return fail(EnumSvrResult.ERROR_RESOURCE_DELETE);
        }else{
            resourceService.deleteRoleResource(id);
        }
        return success();
    }

    @GetMapping(value="{id}/select")
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {
        TbResource resource = resourceService.selectById(id);
        List<TbResource> list = resourceService.selectByMap(null);
        List<Select2Entity> select2List = new TreeUtil().getSelectTree(list, null);
        map.put("resources", select2List);
        map.put("resource", resource);
        return "resource/edit";
    }

    @GetMapping("icon")
    public String icon() {
        return "resource/icon";
    }

}
