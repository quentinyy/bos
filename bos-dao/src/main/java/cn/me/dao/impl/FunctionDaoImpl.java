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
}
