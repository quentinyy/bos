package cn.me.service;

import cn.me.domain.User;

public interface IUserService {
    User login(User model);

    void editPwd(String password, String id);
}