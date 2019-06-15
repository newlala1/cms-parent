package org.xh.cms.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2019/6/14.
 */

public class UrlAccessDecisionManager implements AccessDecisionManager {
    RoleVoter roleVoter;
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        if(Arrays.stream("/js,/img,/css,/index".split(",")).allMatch(x->{
            return !o.toString().startsWith(x);
        })==true) {
            if (collection.stream()
                    .anyMatch(
                            x -> {
                                return CollectionUtils.containsAny(
                                        authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                                        collection.stream()
                                                .map(y -> {
                                                    return y.getAttribute().split(":")[1].split(",");
                                                })
                                                .flatMap(Arrays::stream)
                                                .collect(Collectors.toList())
                                );
                            }
                    ) == false) {
                //System.out.println(o.toString());
                //throw new AccessDeniedException(o.toString());
            }
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
    public static void main(String[] args){



    }
}
