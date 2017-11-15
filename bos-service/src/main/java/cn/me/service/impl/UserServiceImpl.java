package cn.me.service.impl;

import cn.me.dao.IUserDao;
import cn.me.domain.Function;
import cn.me.domain.Role;
import cn.me.domain.User;
import cn.me.service.IUserService;
import cn.me.utils.MD5Utils;
import cn.me.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    public User login(User model) {
        User user = userDao.findUserByNameAndPwd(model.getUsername(), MD5Utils.md5(model.getPassword()));
        return user;
    }

    public void editPwd(String password, String id) {
        userDao.execUpdate("user.editPwd",password,id);
    }

    @Override
    public void save(User model, String[] roleIds) {
        model.setPassword(MD5Utils.md5(model.getPassword()));
        userDao.save(model);
        if(roleIds !=null && roleIds.length>0){
            for (String roleid:roleIds
                 ) {
                Role role = new Role(roleid);
                model.getRoles().add(role);
            }
        }
    }

    @Override
    public void queryPage(PageBean pageBean) {
        userDao.pageQuery(pageBean);
    }


}
