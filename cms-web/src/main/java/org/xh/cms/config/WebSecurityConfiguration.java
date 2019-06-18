package org.xh.cms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.xh.cms.security.MySecurityFilter;
import org.xh.cms.security.UrlAccessDecisionManager;
import org.xh.cms.service.CustomUserDetailService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WebSecurityConfiguration
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 9:24
 * @ModifyDate 2019/6/14 9:24
 * @Version 1.0
 */
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }
    @Bean
    public AccessDecisionManager accessDecisionManager(){
        return new UrlAccessDecisionManager();
    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
    @Bean
    public MySecurityFilter mySecurityFilter(){
        MySecurityFilter f=new MySecurityFilter();
        f.setAccessDecisionManager(accessDecisionManager());
        f.setSecurityMetadataSource((FilterInvocationSecurityMetadataSource)userDetailsService());
        return f;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(true);

        auth.authenticationProvider(provider);
        RoleVoter voter;

    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.addFilterAfter(mySecurityFilter(), FilterSecurityInterceptor.class)
                .authorizeRequests()
                .antMatchers("/js/**","/img/**","/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .successForwardUrl("/index")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        Map<String,Object> result=new HashMap<>();
                        result.put("state","0");
                        result.put("msg","success");
                        httpServletResponse.getWriter().write(objectMapper().writeValueAsString(result));
                    }
                })
                .failureForwardUrl("/login?error")
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        Map<String,Object> result=new HashMap<>();
                        result.put("state","-1001");
                        result.put("msg","Not Login");
                        httpServletResponse.getWriter().write(objectMapper().writeValueAsString(result));
                    }
                })
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .cors().disable()
                .csrf().disable();
    }
}
