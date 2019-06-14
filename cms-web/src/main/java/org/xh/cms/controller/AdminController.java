package org.xh.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2019/6/14.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/add")
    public String add(){
        return "admin/add";
    }
    @RequestMapping("/list")
    public String list(){
        return "admin/list";
    }

}
