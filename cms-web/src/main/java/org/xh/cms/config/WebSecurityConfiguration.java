package org.xh.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.annotation.Jsr250MethodSecurityMetadataSource;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.xh.cms.security.MySecurityFilter;
import org.xh.cms.security.UrlAccessDecisionManager;
import org.xh.cms.service.CustomUserDetailService;

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
    public MySecurityFilter mySecurityFilter(){
        MySecurityFilter f=new MySecurityFilter();
        f.setAccessDecisionManager(accessDecisionManager());
        f.setSecurityMetadataSource(new CustomUserDetailService());
        return f;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(false);

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
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .cors().disable()
                .csrf().disable();
    }


}
