package cn.me.dao;

import cn.me.dao.base.BaseDao;
import cn.me.domain.User;

public interface IUserDao extends BaseDao<User>{
    User findUserByNameAndPwd(String name, String password);
    User findUserByUsername(String username);
}
