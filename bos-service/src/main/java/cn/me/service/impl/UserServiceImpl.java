package cn.me.service.impl;

import cn.me.dao.UserDao;
import cn.me.domain.User;
import cn.me.service.UserService;
import cn.me.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    public User login(User model) {
        User user = userDao.findUserByNameAndPwd(model.getName(), MD5Utils.md5(model.getPassword()));
        return user;
    }
}
