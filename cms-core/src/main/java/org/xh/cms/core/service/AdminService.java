package org.xh.cms.core.service;

import org.xh.cms.core.model.Admin;
import org.xh.cms.core.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName AdminService
 * @Description TODO
 * @Author xuhui
 * @Date 2019/6/14 9:04
 * @ModifyDate 2019/6/14 9:04
 * @Version 1.0
 */
@Service
@Transactional
public class AdminService implements BaseService<Admin,String> {
    @Autowired
    private AdminDao adminDao;
    @Override
    public Admin findById(String id){
        return adminDao.findById(id).orElse(null);
    }

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    public Admin findByUserName(String userName){
        return adminDao.findByUserName(userName);
    }
}
