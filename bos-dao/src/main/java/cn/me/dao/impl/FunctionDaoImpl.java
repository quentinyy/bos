package cn.me.dao.impl;

import cn.me.dao.IFunctionDao;
import cn.me.dao.base.impl.BaseDaoImpl;
import cn.me.domain.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function>  implements IFunctionDao{
    public List<Function> findAll() {
        String hql = "FROM Function f WHERE f.parentFunction IS NULL";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public List<Function> findFunctionByUserId(String id) {
        String hql = "select distinct f from Function f left outer join f.roles " +
                "r left outer join r.users u where u.id = ?";
        return (List<Function>) this.getHibernateTemplate().find(hql,id);
    }

    @Override
    public List<Function> findMenu(String id) {
        String hql = "select distinct f from Function f left outer join f.roles " +
                "r left outer join r.users u where u.id = ? and f.generatemenu='1'";
        return (List<Function>) this.getHibernateTemplate().find(hql,id);
    }
}
