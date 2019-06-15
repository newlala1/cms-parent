package org.xh.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import org.xh.cms.core.dao.PermissionDao;
import org.xh.cms.core.model.Admin;
import org.xh.cms.core.model.Permission;
import org.xh.cms.core.model.Role;
import org.xh.cms.core.service.PermissionService;
import org.xh.cms.service.CustomUserDetailService;

import java.util.*;

import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @ClassName PermissionController
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 11:40
 * @ModifyDate 2019/6/14 11:40
 * @Version 1.0
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listall(){
        return "permission/list";
    }
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public List<Permission> list(@RequestParam(name = "loadall",defaultValue = "false",required = false) boolean loadall){
        List<Permission> permissionList=permissionService.findAll();

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailService.CustomUserDetails userDetails=(CustomUserDetailService.CustomUserDetails)authentication.getPrincipal();
        Set<Role> roleSet=userDetails.getRoleSet();
        List<Permission> result= permissionList.stream()
                .filter(x->{
                    return CollectionUtils.containsAny(x.getRolesSet(),roleSet);
                })
                .collect(toList());
        if(loadall==false)
            result=combinePermission(result,null);
        else
            result=combinePermission(permissionList,null);
        return result;
    }
    private List<Permission> combinePermission(List<Permission> permissionList, Permission parentPermission){
        List<Permission> result=new ArrayList<Permission>();
        if(parentPermission==null){
            for(Permission p:permissionList){
                if(p.getParentPermission()==null) {
                    result.add(p);
                    combinePermission(permissionList, p);
                }
            }
        }else{
            if(parentPermission!=null) {
                Set<Permission> l = permissionList.stream().filter(x ->x.getParentPermission()!=null && x.getParentPermission().getId().equals(parentPermission.getId())).collect(toSet());
                parentPermission.setChildrenPermission(l);
                l.stream().forEach(x -> {
                    combinePermission(permissionList, x);
                });
            }
        }
        return result;
    }

}
