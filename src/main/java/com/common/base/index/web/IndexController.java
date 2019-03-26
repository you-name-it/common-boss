package com.common.base.index.web;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.base.common.bean.AbstractBean;
import com.common.base.common.exception.EnumSvrResult;
import com.common.base.resource.entity.TbResource;
import com.common.base.resource.service.ITbResourceService;
import com.common.base.user.entity.User;
import com.common.base.user.service.UserService;
import com.common.utils.EndecryptUtils;
import com.feilong.core.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户账户表 前端控制器
 * </p>
 *
 * @author xj
 * @since 2016-12-20
 */
@Controller
public class IndexController extends BaseController{
    private Logger logger = LogManager.getLogger(IndexController.class.getName());
    @Autowired
    private UserService userService;

    @Resource
    private ITbResourceService resourceService;


    @RequestMapping({"/","/index" })
    public String index(Map<String, Object> map) {
        User userEntity = (User) SecurityUtils.getSubject().getPrincipal();
        //通过角色查询资源列表    1 表示 管理员
        List<TbResource> treeMenuList = resourceService.findAllResourcesMenu();
        map.put("menus", treeMenuList);

        return "index";
    }

    @RequestMapping("submitSuccess")
    public String test(){
        return "common/submitSuccess";
    }

    //跳转页面
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register (HttpServletResponse response , @RequestHeader HttpHeaders headers){


        return "register";
    }


    //跳转页面
    @RequestMapping(value = "/retrievePassword",method = RequestMethod.GET)
    public String retrievePassword (HttpServletResponse response , @RequestHeader HttpHeaders headers){


        return "retrievePassword";
    }


    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(HttpServletResponse response, HttpServletRequest request, @RequestHeader HttpHeaders header){
        Subject subject = SecurityUtils.getSubject();


        if(Validator.isNotNullOrEmpty(header.get("X-Requested-With"))){
            response.setHeader("sessionstatus", "timeout");

        }
        return "login";
    }
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    /*@ResponseBody*/
    public String doLogin(HttpServletRequest request , Map<String, Object> map, String username,String password,String rememberMe) {

        StringBuffer requestURL = request.getRequestURL();
        String msg = "";
        if(username.equals("")||username.equals(null)||password.equals("")||username.equals(null)){
             msg = "请输入账户名或密码!";
            map.put("msg", msg);
            return "login";
        }else{
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>{},{},{}",username,password,rememberMe);

            //判断用户是同过手机号码登陆还是通过邮箱登陆
            if(username.contains("@")){
                //邮箱
                //通过邮箱查询该邮箱记录的手机号码
                User EmailUser = this.userService.selectOne(new Wrapper<User>() {
                    @Override
                    public String getSqlSegment() {
                        return null;
                    }
                }.eq("EMAIL", username));
                //User EmailUser = userService.selectUserRole(ConvertUtil.toMap("email",(Object)username));
                if(EmailUser==null){
                    throw new UnknownAccountException();
                }
                username = EmailUser.getPhone();
            }

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(rememberMe==""?false:true);
            Subject subject = SecurityUtils.getSubject();
            subject.getSession().setAttribute("account", username);
            try{
                subject.login(token);
                User user = new User();
                user.setLoginTime(new Date());
                //更新用户的登陆时间
                this.userService.update(user,Condition.instance().eq("PHONE",username));
                logger.info("{}登陆成功!",username);
                subject.getSession().removeAttribute("msg");


                SavedRequest savedRequest= WebUtils.getSavedRequest(request);

                if(null!=savedRequest){
                    System.out.println("注意登录之前的路径是"+savedRequest.getRequestUrl());
                    System.out.println(savedRequest.getRequestUrl().substring(8));
                    return "redirect:" + savedRequest.getRequestUrl();
                }

                return "redirect:/index";
            }catch(UnknownAccountException e){
                msg = "账户不存在！";
            }catch(IncorrectCredentialsException e){
                msg = "密码错误！";
            }catch (LockedAccountException e) {
                msg = "您的账户已被锁定,请与管理员联系！";
            }catch(ExcessiveAttemptsException e){
                msg = "登录失败次数过多,请稍后再试！";
            }catch(Exception e){
                e.printStackTrace();
                msg="系统发生错误，请联系管理员！";
            }
            map.put("msg", msg);
            // 此方法不处理登录成功,由shiro进行处理.
            logger.info("{}登陆失败，error={}!",username,msg);
            subject.getSession().setAttribute("msg", msg);


            SecurityUtils.getSubject().getSession().removeAttribute("userSession");

            return "login";
        }



    }

    @GetMapping("/logout")
    public String logouts(HttpServletRequest request) {
        // 注销登录
        SecurityUtils.getSubject().logout();
        return "redirect:login";
    }

    /*
     * 用户注册
     *
     * 不用实体接收是预防恶意填写信息
     *
     * */
    @PostMapping("register")
    @ResponseBody
    public AbstractBean register(Map<String,Object> map, HttpServletRequest request, @RequestParam(value = "id",required = false) String id ,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("email") String email,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("VerificationCode") String VerificationCode,
                                 @RequestParam("password") String password,
                                 @RequestParam("rePassword") String rePassword){

        //检测用户是否注册过
        //校验手机号码
        Object obj1 = this.userService.selectObj(Condition.instance().eq("PHONE", phone));

        if(obj1!=null){
            return fail("该手机已注册过，请更换手机再试！",EnumSvrResult.ERROR.getVal());
        }

        Object obj2 = this.userService.selectObj(Condition.instance().eq("EMAIL", email));

        if(obj2!=null){
            return fail("该邮箱已注册过，请更换邮箱再试！",EnumSvrResult.ERROR.getVal());
        }

        //判断验证码是否匹配
        HttpSession httpSession=request.getSession();
        String checkCode = (String) httpSession.getAttribute("checkCode");

        if(!VerificationCode.equals(checkCode)){
            return fail("验证码错误或超时，请重试!",EnumSvrResult.ERROR.getVal());
        }

        if(!rePassword.equals(password)){
            return fail("两次输入的密码不一致，请重试!", EnumSvrResult.ERROR.getVal());
        }

        User user = new User();

        // 加密用户输入的密码，得到密码和加密盐，保存到数据库
        User userEntity = EndecryptUtils.md5Password(phone, password, 2);
        //设置添加用户的密码和加密盐
        user.setUserName(userName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(userEntity.getPassword());
        user.setCredentialsSalt(userEntity.getCredentialsSalt());
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setUpdateTime(new Date(System.currentTimeMillis()));
        //VIP状态：是否为VIP
        user.setVipStatus("0");
        user.setCompanyStatus("0");
        //设置锁定状态：未锁定；删除状态：未删除
        user.setLocked("0");

        boolean result = userService.insertAll(user);
        if(!result)
        {
            return fail(EnumSvrResult.ERROR);
        }

        return success();
    }

    /*
    * 找回密码
    * */
    @PostMapping("retrievePassword")
    @ResponseBody
    public AbstractBean retrievePassword(Map<String,Object> map, HttpServletRequest request,
                                         @RequestParam("phone") String phone,
                                         @RequestParam("VerificationCode") String VerificationCode,
                                         @RequestParam("password") String password,
                                         @RequestParam("rePassword") String rePassword){


        //校验手机号码是否存在
        Object obj1 = this.userService.selectObj(Condition.instance().eq("PHONE", phone));

        if(obj1==null){
            return fail("该手机号码未注册，请使用已注册的手机号码再试！",EnumSvrResult.ERROR.getVal());
        }

        //判断验证码是否匹配
        HttpSession httpSession=request.getSession();
        String checkCode = (String) httpSession.getAttribute("checkCode");

        if(!VerificationCode.equals(checkCode)){
            return fail("验证码错误或超时，请重试!",EnumSvrResult.ERROR.getVal());
        }

        if(!rePassword.equals(password)){
            return fail("两次输入的密码不一致，请重试!", EnumSvrResult.ERROR.getVal());
        }

        User user = new User();

        // 加密用户输入的密码，得到密码和加密盐，保存到数据库
        User userEntity = EndecryptUtils.md5Password(phone, password, 2);
        //设置添加用户的密码和加密盐
        user.setPassword(userEntity.getPassword());
        user.setCredentialsSalt(userEntity.getCredentialsSalt());
        user.setUpdateTime(new Date(System.currentTimeMillis()));
        //设置锁定状态：未锁定；删除状态：未删除
        user.setLocked("0");
        boolean result = userService.update(user,Condition.instance().eq("PHONE",phone));
        if(!result)
        {
            return fail(EnumSvrResult.ERROR);
        }

        return success();

    }

    /*
    * 检测邮箱是否注册过
    * */
    @GetMapping("checkEmail")
    @ResponseBody
    public AbstractBean checkEmail(@RequestParam(value="email") String email){

        Object em = this.userService.selectObj(Condition.instance().eq("EMAIL", email));

        if(em==null){
            return success();
        }
        return fail("该邮箱已注册过，请更换邮箱重试!",EnumSvrResult.ERROR.getVal());

    }


    /*
     * 检测邮箱是否注册过
     * */
    @GetMapping("checkPhone")
    @ResponseBody
    public AbstractBean checkPhone(@RequestParam(value="phone") String phone){

        Object ph = this.userService.selectObj(Condition.instance().eq("PHONE", phone));

        if(ph==null){
            return success();
        }
        return fail("该手机已注册过，请更换手机重试!",EnumSvrResult.ERROR.getVal());

    }
}
