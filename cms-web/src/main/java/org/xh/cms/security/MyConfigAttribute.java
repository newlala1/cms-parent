package org.xh.cms.security;

import org.springframework.security.access.ConfigAttribute;
import org.xh.cms.core.model.Role;

import java.util.List;

/**
 * @ClassName MyConfigAttribute
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/17 9:46
 * @ModifyDate 2019/6/17 9:46
 * @Version 1.0
 */
public class MyConfigAttribute implements ConfigAttribute {
    //地址
    private String attribute;
    //角色
    private List<Role> roles;
    public MyConfigAttribute(String attribute,List<Role> roles){
        this.attribute=attribute;
        this.roles=roles;
    }
    @Override
    public String getAttribute() {
        return this.attribute;
    }
    public List<Role> getUrls(){
        return this.roles;
    }
}
