package com.common.enterprise.web;

import com.baomidou.mybatisplus.mapper.Condition;
import com.common.base.common.bean.AbstractBean;
import com.common.base.common.exception.EnumSvrResult;
import com.common.base.index.web.BaseController;
import com.common.base.user.entity.User;
import com.common.base.user.service.UserService;
import com.common.enterprise.entity.Company;
import com.common.enterprise.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 〈企业模块〉
 *
 * @author HuangQiuRong
 * @create 2019/1/11
 */
@Controller
@RequestMapping("/enterprise")
public class EnterpriseController extends BaseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private UserService userService;





    /*
    * 企业认证
    * */
    @PostMapping("save")
    @ResponseBody
    public AbstractBean save(Map<String,Object> map, HttpServletRequest request, @RequestParam("name") String name,
                             @RequestParam("creditCode") String creditCode,
                             @RequestParam("address") String address,
                             @RequestParam("email") String email,
                             @RequestParam("telephone") String telephone,
                             @RequestParam(value = "businessLicenseImgUrl",required = false) String businessLicenseImgUrl){

        User user = (User) request.getSession().getAttribute("userSession");

        user.setCompanyStatus("3");
        this.userService.updateById(user);
        request.getSession().setAttribute("userSession",this.userService.selectById(user.getID()));

        Company company = new Company();

        //需要检验企业信用代码是否存在
        Company companResult = this.enterpriseService.selectOne(Condition.instance().eq("CREDITCODE", creditCode));

        if(companResult!=null){
            return fail("该公司已注册",EnumSvrResult.ERROR.getVal());
        }

        company.setName(name);
        company.setCreditCode(creditCode);
        company.setAddress(address);
        company.setEmail(email);
        company.setTelephone(telephone);
        company.setBusinessLicenseImgUrl(businessLicenseImgUrl);
        company.setStatus("3");
        company.setcTime(new Date());
        company.setuTime(new Date());

        //获取当前登陆的账号，并给账号设置companyID
        User sessionUser = (User) request.getSession().getAttribute("userSession");

        Boolean result = this.enterpriseService.insertAndUpdateUser(company,sessionUser);

        if(!result){
            return fail(EnumSvrResult.ERROR);
        }

        return success();
    }


}
