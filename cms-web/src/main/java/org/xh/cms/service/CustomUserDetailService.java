package org.xh.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.xh.cms.core.model.Admin;
import org.xh.cms.core.model.Role;
import org.xh.cms.core.service.AdminService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private AdminService adminService;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin=adminService.findByUserName(s);
        CustomUserDetails customUserDetails=new CustomUserDetails(admin);
        return customUserDetails;
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
            return !admin.getUserStatus().equals(UserStatus.LOCKED);
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
