package com.common.base.role.web;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.common.bean.AbstractBean;
import com.common.base.common.exception.EnumSvrResult;
import com.common.base.common.model.JSTreeEntity;
import com.common.base.index.web.BaseController;
import com.common.base.resource.entity.TbResource;
import com.common.base.resource.service.ITbResourceService;
import com.common.base.role.entity.TbRole;
import com.common.base.role.service.ITbRoleService;
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
 * <p>
 * 角色表  前端控制器
 * </p>
 *
 * @author laizhilong
 * @since 2017-04-12
 */
@Controller
@RequestMapping("/role/")
public class TbRoleController extends BaseController {

    @Autowired
    private ITbRoleService roleService;

    @Autowired
    private ITbResourceService resourceService;

    public TbRoleController() {
    }

    @GetMapping("listUI")
    public String listUI() {
        return "role/list";
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
        Page<TbRole> list = roleService.selectPage(new Page<TbRole>(pager.getNowPage(), pager.getPageSize()), Condition.instance().allEq(parameters));
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
        return "role/form";
    }

    @PostMapping("save")
    @ResponseBody
    public AbstractBean add(TbRole role){
        if(role.getId()==null){
            role.setCreateTime(new Date(System.currentTimeMillis()));
            role.setUpdateTime(new Date(System.currentTimeMillis()));
        }else
        {
            role.setUpdateTime(new Date(System.currentTimeMillis()));
        }

        if(!roleService.insertOrUpdate(role)){
            return fail(EnumSvrResult.ERROR);
        }
        return success();
    }

    @DeleteMapping("{roleId}/delete")
    @ResponseBody
    public AbstractBean delete(@PathVariable(required=true) Integer roleId) {
        if(!roleService.deleteRoleResource(roleId)){
            return fail(EnumSvrResult.ERROR);
        }
        return success();
    }

    @GetMapping("{roleId}/select")
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer roleId) {
        TbRole role = roleService.selectById(roleId);
        map.put("role", role);
        return "role/edit";
    }

    @GetMapping("{roleId}/permission")
    public String permission(Map<String,Object> map,@PathVariable(required=true) Integer roleId) {
        TbRole role = roleService.selectById(roleId);
        List<TbResource> resources = resourceService.queryResourceList(ConvertUtil.toMap("isHide",(Object)0,"roleId",(Object)roleId));
        List<JSTreeEntity> jstreeList = new TreeUtil().generateJSTree(resources);
        map.put("role", role);
        map.put("resources", jstreeList);
        return "role/permission";
    }

    @GetMapping("{roleId}/getPermission")
    @ResponseBody
    public Object getPermission(@PathVariable(required=true) Integer roleId) {
        List<TbResource> resources = resourceService.queryResourceList(ConvertUtil.toMap("isHide",(Object)0,"roleId",(Object)roleId));
        List<JSTreeEntity> jstreeList = new TreeUtil().generateJSTree(resources);
        return jstreeList;
    }

    @PostMapping("savePermission")
    @ResponseBody
    public AbstractBean permission(int roleId, @RequestParam("resourceIds[]") List<Integer> resourceIds){
        roleService.savePermission(roleId,resourceIds);
        return success();
    }
}
