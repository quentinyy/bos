package cn.me.dao;

import cn.me.dao.base.BaseDao;
import cn.me.domain.Function;

import java.util.List;

public interface IFunctionDao extends BaseDao<Function>{
    public List<Function> findAll();
}
