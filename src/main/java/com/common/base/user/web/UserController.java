package com.common.base.user.web;

import com.common.base.common.bean.AbstractBean;
import com.common.base.common.exception.EnumSvrResult;
import com.common.base.index.web.BaseController;
import com.common.base.user.entity.User;
import com.common.base.user.service.UserService;
import com.common.enterprise.entity.Company;
import com.common.enterprise.service.EnterpriseService;
import com.common.utils.DateUtils;
import com.common.utils.EndecryptUtils;
import com.feilong.core.bean.ConvertUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author HuangQiuRong
 * @create 2019/1/3
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private EnterpriseService enterpriseService;

    /*
    * 跳转到用户信息的主页
    * */
    @GetMapping("index")
    public String index(HttpServletRequest request,Map<String,Object> map) {

        User user = (User) request.getSession().getAttribute("userSession");

        map.put("userEntity",user);

        return "user/index";
    }

    /*
    * 用户详情页面
    * */
    @RequestMapping(value = "info",method = RequestMethod.GET)
    public String info(HttpServletRequest request,Map<String,Object> map,@RequestParam(required = false) String type) {




        User user = (User) request.getSession().getAttribute("userSession");

        User userEntity = this.userService.selectById(user.getID());

        Company company = this.enterpriseService.selectById(userEntity.getCompanyID());

        if(company==null){
            company = new Company();
        }

        String birthDay = "";

        if(userEntity.getBirthDay()!=null){
            birthDay = DateUtils.dateToStr(userEntity.getBirthDay());
        }else{
            birthDay = DateUtils.dateToStr(new Date());
        }


        if(type!=null){
            map.put("type" ,"1");
        }else{
            map.put("type" ,"2");
        }

        map.put("company",company);
        map.put("birthDay",birthDay);
        map.put("userEntity",userEntity);

        return "user/info";
    }

    /*
    * 用户账号管理页面
    * */
    @GetMapping("accountManagement")
    public String accountManagement(HttpServletRequest request,Map<String,Object> map){

        User user = (User) request.getSession().getAttribute("userSession");

        User userEntity = this.userService.selectById(user.getID());

        map.put("userEntity",userEntity);

        return "user/accountManagement";
    }

    /*
    * 用户修改密码页面
    * */
    @GetMapping("changePassword")
    public String changePassword(HttpServletRequest request,Map<String,Object> map){

        User user = (User) request.getSession().getAttribute("userSession");

        User userEntity = this.userService.selectById(user.getID());

        map.put("userEntity",userEntity);

        return "user/changePassword";
    }

    /*
    * 用户企业认证页面
    * */
    @GetMapping("enterpriseAuthentication")
    public String enterpriseAuthentication(HttpServletRequest request,Map<String,Object> map){

        User user = (User) request.getSession().getAttribute("userSession");

        User userEntity = this.userService.selectById(user.getID());

        Company company = this.enterpriseService.selectById(userEntity.getCompanyID());

        if(company==null){
            company = new Company();
        }

        String birthDay = DateUtils.dateToStr(userEntity.getBirthDay());

        //代表是访问企业信息页面
        map.put("enterpriseInfo","enterpriseInfo");
        map.put("company",company);
        map.put("birthDay",birthDay);
        map.put("userEntity",userEntity);

        return "user/info";
    }


    /*
    * 修改密码
    *
    * */
    @PostMapping("updatePassword")
    @ResponseBody
    public Object updatePassword(HttpServletRequest request,Map<String,Object> map,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("verifyPassword") String verifyPassword){

        if(!newPassword.equals(verifyPassword)){
            return fail("两次输入的密码不一致，请重试!",EnumSvrResult.ERROR.getVal());
        }

        User user = (User) request.getSession().getAttribute("userSession");

        //判断原来的密码是否正确
        //重新登陆一遍，如果登陆成功，说明验证原来密码正确
        UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(), oldPassword);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            //如果过了这里不抛异常则表示原来密码验证成功
            User userEntity = userService.selectById(user.getID());

            User userFlag = EndecryptUtils.md5Password(user.getPhone(), newPassword, 2);
            //设置添加用户的密码和加密盐
            userEntity.setPassword(userFlag.getPassword());
            userEntity.setCredentialsSalt(userFlag.getCredentialsSalt());
            userEntity.setUpdateTime(new Date(System.currentTimeMillis()));
            if(!userService.updateById(userEntity)){
                return fail(EnumSvrResult.ERROR);
            }

            //修改成功，清除session中的信息，并跳转到登陆页面重新登陆   //logout 会清空session
            //request.getSession().removeAttribute("userSession");
            //退出
            SecurityUtils.getSubject().logout();

        }catch(IncorrectCredentialsException e){
            return fail("原密码错误！",EnumSvrResult.ERROR.getVal());
        }catch (LockedAccountException e) {
            return fail("您的账户已被锁定,请与管理员联系！",EnumSvrResult.ERROR.getVal());
        }catch(ExcessiveAttemptsException e){
            return fail("登录失败次数过多,请稍后再试！",EnumSvrResult.ERROR.getVal());
        }catch(Exception e){
            e.printStackTrace();
            return fail("系统发生错误，请联系管理员！",EnumSvrResult.ERROR.getVal());
        }

        return success();
    }


    /*
    * 更换用户手机号码，登录账号
    * */
    @PostMapping("changePhone")
    @ResponseBody
    public AbstractBean changePhone(HttpServletRequest request,Map<String,Object> map,
                                    @RequestParam("password") String password,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("newPhone") String newPhone,
                                    @RequestParam("VerificationCode") String VerificationCode){

        AbstractBean ab = new AbstractBean();

        //检查新手机号码是否存在
        User user = userService.selectUserRole(ConvertUtil.toMap("phone",(Object)newPhone));

        if(user!=null){
            return fail("该手机号码已注册过，请重新填写。",EnumSvrResult.ERROR.getVal());
        }

        //验证短信验证码是否正确
        HttpSession httpSession=request.getSession();
        String checkCode = (String) httpSession.getAttribute("checkCode");

        if(!VerificationCode.equals(checkCode)){
            return fail("验证码错误或超时，请重试!",EnumSvrResult.ERROR.getVal());
        }

        user = (User) request.getSession().getAttribute("userSession");

        //验证密码是否正确
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            //如果过了这里不抛异常则表示原来密码验证成功
            User userEntity = userService.selectById(user.getID());

            //修改账号
            User userFlag = EndecryptUtils.md5Password(newPhone, password, 2);
            //设置添加用户的密码和加密盐
            userEntity.setPhone(newPhone);
            userEntity.setPassword(userFlag.getPassword());
            userEntity.setCredentialsSalt(userFlag.getCredentialsSalt());
            userEntity.setUpdateTime(new Date(System.currentTimeMillis()));
            if(!userService.updateById(userEntity)){
                return fail(EnumSvrResult.ERROR);
            }


            //删除验证码
            //httpSession.removeAttribute("checkCode");     已设置定时五分钟自动删除
            //修改成功，清除session中的信息，并跳转到登陆页面重新登陆
            //request.getSession().removeAttribute("userSession");
            //退出
            SecurityUtils.getSubject().logout();

        }catch(IncorrectCredentialsException e){
            return fail("原密码错误！",EnumSvrResult.ERROR.getVal());
        }catch (LockedAccountException e) {
            return fail("您的账户已被锁定,请与管理员联系！",EnumSvrResult.ERROR.getVal());
        }catch(ExcessiveAttemptsException e){
            return fail("密码验证失败次数过多,请稍后再试！",EnumSvrResult.ERROR.getVal());
        }catch(Exception e){
            e.printStackTrace();
            return fail("系统发生错误，请联系管理员！",EnumSvrResult.ERROR.getVal());
        }

        ab.setMessage("更换成功,请重新登录！");
        ab.setStatus("0");

        return ab;

    }


    /*@PostMapping("save")
    @ResponseBody
    public AbstractBean add(Map<String,Object> map, TbUser user){
        if(user.getId()==null){
            // 加密用户输入的密码，得到密码和加密盐，保存到数据库
            TbUser userEntity = EndecryptUtils.md5Password(user.getAccountName(), user.getPassword(), 2);
            //设置添加用户的密码和加密盐
            user.setPassword(userEntity.getPassword());
            user.setCredentialsSalt(userEntity.getCredentialsSalt());
            //设置创建者姓名
            user.setCreatorName(getUserEntity().getAccountName());
            user.setCreateTime(new Date(System.currentTimeMillis()));
            user.setUpdateTime(new Date(System.currentTimeMillis()));
            //设置锁定状态：未锁定；删除状态：未删除
            user.setLocked(0);
            user.setDeleteStatus(0);
            boolean result = userService.insertAll(user);
            if(!result)
            {
                return fail(EnumSvrResult.ERROR);
            }
        }else{
            userService.updateAll(user);
        }
        return success();
    }*/

    /*
    * 完善用户信息
    *
    * */
    @PostMapping("saveUserInfo")
    @ResponseBody
    public AbstractBean saveUserInfo(Map<String,Object> map, HttpServletRequest request, @RequestParam("id") Integer id ,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("birthDay") String birthDay,
                                 @RequestParam("gender") String gender,
                                 @RequestParam("province") String province,
                                 @RequestParam("city") String city,
                                 @RequestParam("counties") String counties,
                                 @RequestParam("site") String site){

        Date date = DateUtils.strToDate(birthDay);

        User user = new User();

        user.setID(id);
        user.setUserName(userName);
        user.setBirthDay(date);
        user.setGender(gender);
        user.setProvince(province);
        user.setCity(city);
        user.setCounties(counties);
        user.setSite(site);
        user.setUpdateTime(new Date());

        boolean result = userService.updateById(user);
        if(!result)
        {
            return fail(EnumSvrResult.ERROR);
        }

        return success();
    }






   /* @DeleteMapping("{userId}/delete")
    @ResponseBody
    public AbstractBean delete(@PathVariable(required=true) Integer userId) {
        if(!userService.delUser(userId)){
            return fail(EnumSvrResult.ERROR);
        }
        return success();
    }

    @GetMapping("{userId}/select")
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer userId) {
        TbUser user = userService.selectUserRole(ConvertUtil.toMap("userId",(Object)userId));
        List<TbRole> list = roleService.selectByMap(null);
        map.put("roles", list);
        map.put("user", user==null?new TbUser():user);
        return "user/edit";
    }

    @GetMapping("{userId}/toRestPassword")
    public String restPassword(Map<String,Object> map,@PathVariable(required=true) Integer userId) {
        TbUser user = userService.selectById(userId);
        map.put("user", user);
        return "user/rest";
    }*/

    /*@PostMapping(value = "restPassword")
    @ResponseBody
    public AbstractBean restPassword(Map<String,Object> map,TbUser user){
        TbUser userEntity = userService.selectById(user.getId());
        TbUser userFlag = EndecryptUtils.md5Password(user.getAccountName(), user.getPassword(), 2);
        //设置添加用户的密码和加密盐
        userEntity.setPassword(userFlag.getPassword());
        userEntity.setCredentialsSalt(userFlag.getCredentialsSalt());
        userEntity.setUpdateTime(new Date(System.currentTimeMillis()));
        if(!userService.updateById(userEntity)){
            return fail(EnumSvrResult.ERROR);
        }
        return success();
    }*/
}
