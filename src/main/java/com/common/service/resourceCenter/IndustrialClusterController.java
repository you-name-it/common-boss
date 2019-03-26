package com.common.service.resourceCenter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Date:2018/12/19
 * Author:QR.Huang
 */
@Controller
@RequestMapping("/resourceCenter/industrialCluster")
public class IndustrialClusterController {

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String info (HttpServletResponse response , @RequestHeader HttpHeaders headers){


        return "resourceCenter/industrialCluster";
    }

}
