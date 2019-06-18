package org.xh.cms.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/6/14.
 */

public class UrlAccessDecisionManager implements AccessDecisionManager {
    RoleVoter roleVoter;
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        List<String> userRoleName= authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        List<String> expectedRoleName=collection.stream().map(ConfigAttribute::getAttribute).collect(Collectors.toList());
        if(!CollectionUtils.containsAny(userRoleName,expectedRoleName)){
            throw new AccessDeniedException("not denie");
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
