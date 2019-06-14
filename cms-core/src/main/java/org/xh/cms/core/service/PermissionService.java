package org.xh.cms.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xh.cms.core.dao.PermissionDao;
import org.xh.cms.core.model.Permission;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName PermissionService
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 11:41
 * @ModifyDate 2019/6/14 11:41
 * @Version 1.0
 */
@Service
@Transactional
public class PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    public List<Permission> findAll(){
        return permissionDao.findAll();
    }

}
