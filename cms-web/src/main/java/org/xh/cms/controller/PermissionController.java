package org.xh.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xh.cms.core.dao.PermissionDao;
import org.xh.cms.core.model.Permission;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName PermissionController
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 11:40
 * @ModifyDate 2019/6/14 11:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionDao permissionDao;
    @RequestMapping("/list")
    public List<Permission> list(){
        List<Permission> permissionList=permissionDao.findAll();
        return permissionList.stream()
                .filter(x->x.getChildrenPermission()!=null&&x.getChildrenPermission().size()>0)
                .collect(toList());
    }

}
