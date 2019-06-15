package org.xh.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xh.cms.core.service.AdminService;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 9:21
 * @ModifyDate 2019/6/14 9:21
 * @Version 1.0
 */
@Controller
@RequestMapping(value="/")
public class IndexController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value="/index")
    public String index(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return "index";
    }
    @RequestMapping(value="/welcome")
    public String welcome(){
        return "welcome";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }


}
