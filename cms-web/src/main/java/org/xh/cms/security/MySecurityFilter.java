package org.xh.cms.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/6/15.
 */

public class MySecurityFilter  extends FilterSecurityInterceptor{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            if(authentication instanceof AnonymousAuthenticationToken) {
                super.doFilter(request, response, chain);
            }
            else{
                this.getAccessDecisionManager().decide(authentication,null,authentication.getAuthorities().stream().map(x->{
                    return x.getAuthority();
                }).map(x->{return new SecurityConfig(x);}).collect(Collectors.toList()));
                super.doFilter(request, response, chain);

            }

        }
        catch (Exception ex){

            return;
        }

    }
}
