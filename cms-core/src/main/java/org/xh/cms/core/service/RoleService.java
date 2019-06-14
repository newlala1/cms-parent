package org.xh.cms.core.service;

import org.springframework.stereotype.Service;
import org.xh.cms.core.dao.RoleDao;
import org.xh.cms.core.model.Role;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName RoleService
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 9:13
 * @ModifyDate 2019/6/14 9:13
 * @Version 1.0
 */
@Service
@Transactional
public class RoleService implements BaseService<Role,String>{
    private RoleDao roleDao;
    @Override
    public Role findById(String s) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }
}
