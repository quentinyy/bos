package cn.me.service.impl;

import cn.me.dao.IUserDao;
import cn.me.domain.User;
import cn.me.service.IUserService;
import cn.me.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
