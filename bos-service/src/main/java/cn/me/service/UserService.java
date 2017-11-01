package cn.me.service;

import cn.me.domain.User;

public interface UserService {
    User login(User model);

    String editPwd(String password,String id);
}
