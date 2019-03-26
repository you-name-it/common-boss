package com.common.service.FintechCloud;

import com.common.base.user.entity.User;
import com.common.base.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Date:2018/12/19
 * Author:QR.Huang
 */
@Controller
@RequestMapping("/solutionService")
public class solutionServiceController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String info (HttpServletRequest request, Map<String,Object> map ){

        User user = (User) request.getSession().getAttribute("userSession");

        User userEntity = this.userService.selectById(user.getID());

        map.put("userEntity",userEntity);

        return "user/solutionService";
    }

    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String form (HttpServletResponse response , @RequestHeader HttpHeaders headers){


        return "fintechCloud/accountManagementForm";
    }

}
