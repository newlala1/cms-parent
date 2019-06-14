package org.xh.cms.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @ClassName BaseDao
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 9:02
 * @ModifyDate 2019/6/14 9:02
 * @Version 1.0
 */
@NoRepositoryBean
public interface BaseDao<T,ID> extends JpaRepository<T,ID> {
}
