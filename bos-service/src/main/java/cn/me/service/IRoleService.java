package cn.me.service;

import cn.me.domain.Role;
import cn.me.utils.PageBean;

import java.util.List;

public interface IRoleService {
    void save(Role model, String functionIds);

    void queryPage(PageBean pageBean);

    List<Role> findAll();
}
