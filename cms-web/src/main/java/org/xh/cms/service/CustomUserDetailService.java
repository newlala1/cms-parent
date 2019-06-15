package org.xh.cms.service;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.xh.cms.core.model.Admin;
import org.xh.cms.core.model.Permission;
import org.xh.cms.core.model.Role;
import org.xh.cms.core.service.AdminService;
import org.xh.cms.core.service.PermissionService;


import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * @ClassName CustomUserDetailService
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 11:05
 * @ModifyDate 2019/6/14 11:05
 * @Version 1.0
 */
@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService,FilterInvocationSecurityMetadataSource {
    @Autowired
    private AdminService adminService;
    @Autowired
    private PermissionService permissionService;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin=adminService.findByUserName(s);
        CustomUserDetails customUserDetails=new CustomUserDetails(admin);
        return customUserDetails;
    }

    @Cacheable(key="#o",value="attributesByUrl")
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        List<ConfigAttribute> result=null;
        if(o instanceof String){
            result=permissionService.findByPermissionUrl((String)o).stream()
                    .map(x->{
                        return x.getPermissionUrl()+":"+x.getRolesSet().stream().map(Role::getRoleName).collect(joining(","));
                    })
                    .map(SecurityConfig::new)
                    .collect(toList());
        }
        return result;
    }

    @Cacheable()
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<Permission> permissionList=permissionService.findAll();
        List<ConfigAttribute> configAttributeList=permissionList.stream()
                .map(x->{
                    return x.getPermissionUrl()+":"+x.getRolesSet().stream().map(Role::getRoleName).collect(joining(","));
                })
                .map(SecurityConfig::new)
                .collect(toList());
        return configAttributeList;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public class CustomUserDetails extends Admin implements UserDetails{
        private Admin admin;
        public CustomUserDetails(Admin admin){
            this.admin=admin;
        }
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorites=admin.getRoleSet().stream()
                    .map(Role::getRoleName)
                    .map(SimpleGrantedAuthority::new)
                    .collect(toList());
            return authorites;
        }

        @Override
        public String getPassword() {
            return admin.getUserPassword();
        }

        @Override
        public String getUsername() {
            return admin.getUserName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return admin==null?false:!admin.getUserStatus().equals(UserStatus.LOCKED);
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public Set<Role> getRoleSet() {
            return this.admin.getRoleSet();
        }

        @Override
        public boolean isEnabled() {
            return admin.getUserStatus().equals(UserStatus.ENABLED);
        }
    }
}
