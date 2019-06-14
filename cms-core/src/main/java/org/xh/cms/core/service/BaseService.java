package org.xh.cms.core.service;

import java.util.List;

/**
 * @ClassName BaseService
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 13:50
 * @ModifyDate 2019/6/14 13:50
 * @Version 1.0
 */
public interface BaseService<T,ID> {
    T findById(ID id);
    List<T> findAll();

}
