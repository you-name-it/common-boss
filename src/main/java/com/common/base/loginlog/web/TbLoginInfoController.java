package com.common.base.loginlog.web;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.common.bean.AbstractBean;
import com.common.base.index.web.BaseController;
import com.common.base.loginlog.entity.TbLoginInfo;
import com.common.base.loginlog.mapper.TbLoginInfoMapper;
import com.common.base.loginlog.service.ITbLoginInfoService;
import com.common.utils.JsonUtil;
import com.common.utils.dtgrid.model.Pager;
import com.feilong.core.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/loginlog/")
public class TbLoginInfoController extends BaseController {

    @Autowired
    private ITbLoginInfoService loginlogService;

    @Autowired
    private TbLoginInfoMapper loginlogMapper;

    @RequestMapping("listUI")
    public String listUI() {
        return "loginlog/list";
    }

    @PostMapping("list")
    @ResponseBody
    public Object list(String gridPager) {
        Pager pager = JsonUtil.getObjectFromJson(gridPager, Pager.class);
        Map<String, Object> parameters = null;
        String name = "";
        if(Validator.isNullOrEmpty(pager.getParameters())){
            parameters = new HashMap<>();
        }else{
            parameters = pager.getParameters();
            name = parameters.get("account_name").toString();
        }
        Page<TbLoginInfo> list = loginlogService.selectPage(new Page<TbLoginInfo>(pager.getNowPage(), pager.getPageSize()), Condition.instance().like("account_name",name).orderBy("login_time",false));
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

    @DeleteMapping("deleteBatch")
    @ResponseBody
    public AbstractBean delete() {
        loginlogMapper.deleteByMap(null);
        return success();
    }

}
