package org.xh.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xh.cms.core.model.Admin;
import org.xh.cms.core.service.AdminService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * Created by Administrator on 2019/6/14.
 */
@Controller
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/add")
    public String add(){
        return "admin/add";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "admin/list";
    }
    @RequestMapping(value="/list",method = RequestMethod.POST)
    @ResponseBody
    public List<Admin> listJson(){
        List<Admin> adminList=adminService.findAll();
        return adminList;
    }

}
