package org.xh.cms.core.dao;

import org.springframework.stereotype.Repository;
import org.xh.cms.core.model.Permission;

/**
 * @ClassName PermissionDao
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 11:41
 * @ModifyDate 2019/6/14 11:41
 * @Version 1.0
 */
@Repository
public interface PermissionDao extends BaseDao<Permission,String> {
}
