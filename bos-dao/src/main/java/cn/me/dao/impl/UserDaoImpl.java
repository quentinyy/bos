package cn.me.dao.impl;

import cn.me.dao.UserDao;
import cn.me.dao.base.impl.BaseDaoImpl;
import cn.me.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{


    public User findUserByNameAndPwd(String name, String password) {

        String hql = "FROM User u where u.username = ? and u.password = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, name, password);
        if (list != null & list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
