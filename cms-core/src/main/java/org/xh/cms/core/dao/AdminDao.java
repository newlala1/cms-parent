package org.xh.cms.core.dao;

import org.xh.cms.core.model.Admin;
import org.springframework.stereotype.Repository;

/**
 * @ClassName AdminDao
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 9:02
 * @ModifyDate 2019/6/14 9:02
 * @Version 1.0
 */
@Repository
public interface AdminDao extends BaseDao<Admin,String> {
    Admin findByUserName(String userName);
}
