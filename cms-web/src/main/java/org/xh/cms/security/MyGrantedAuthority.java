package org.xh.cms.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @ClassName MyGrantedAuthority
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/17 8:35
 * @ModifyDate 2019/6/17 8:35
 * @Version 1.0
 */
public final class MyGrantedAuthority implements GrantedAuthority{
    private String authority;
    private List<String> urls;
    public MyGrantedAuthority(String authority, List<String> urls){
        this.authority=authority;
        this.urls=urls;
    }
    @Override
    public String getAuthority() {
        return this.authority;
    }
    public List<String> getUrls(){
        return this.urls;
    }
}
