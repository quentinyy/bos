package cn.me.service;

import cn.me.domain.Function;
import cn.me.domain.User;
import cn.me.utils.PageBean;

import java.util.List;

public interface IUserService {
    User login(User model);

    void editPwd(String password, String id);

    void save(User model, String[] roleIds);

    void queryPage(PageBean pageBean);

}
