package cn.me.dao.base.impl;

import cn.me.dao.base.BaseDao;
import cn.me.utils.PageBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.RowCountProjection;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
    private Class<T> entityClass;

    public BaseDaoImpl(){
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        entityClass = (Class<T>) actualTypeArguments[0];
    }

    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass,id);
    }

    public List<T> findAll() {
        String hql = "FROM "+entityClass.getSimpleName();
        return  (List<T>) this.getHibernateTemplate().find(hql);
    }

    public void execUpdate(String queryName,Object...objects) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        int i=0;
        for (Object obj:objects
             ) {
            query.setParameter(i++,obj);
        }
        query.executeUpdate();
    }

    public void pageQuery(PageBean pageBean) {
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        DetachedCriteria criteria = pageBean.getDetachedCriteria();
        criteria.setProjection(Projections.rowCount());
        List<Long> rowCount = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
        Long total = rowCount.get(0);
        pageBean.setTotal(total.intValue());
        criteria.setProjection(null);
        int firstResut = (currentPage-1)*pageSize;
        int maxResult = pageSize;
        criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        List rows = this.getHibernateTemplate().findByCriteria(criteria, firstResut, maxResult);
        pageBean.setRows(rows);
    }

    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }
}
