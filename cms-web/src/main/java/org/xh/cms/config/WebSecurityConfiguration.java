package org.xh.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
//        provider.setUserDetailsService(new CustomUserDetailService());
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setHideUserNotFoundExceptions(false);
//        auth.authenticationProvider(provider).build();
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/js/**","/img/**","/css/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .logout().logoutUrl("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }
}
