package cn.me.dao.base;

import cn.me.utils.PageBean;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    public void save(T entity);
    public void delete(T entity);
    public void update(T entity);
    public T findById(Serializable id);
    public List<T> findAll();
    public void execUpdate(String queryName,Object...objects);
    public void pageQuery(PageBean pageBean);
}
