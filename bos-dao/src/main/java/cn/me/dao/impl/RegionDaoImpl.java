package cn.me.dao.impl;

import cn.me.dao.RegionDao;
import cn.me.dao.base.impl.BaseDaoImpl;
import cn.me.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao{
    public List<Region> findByQ(String q) {
        String hql = "FROM Region r WHERE r.shortcode LIKE ? " +
                "OR r.citycode LIKE ? OR r.province LIKE ? " +
                "OR r.city LIKE ? OR r.district LIKE ?";
        List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql,"%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
        return list;
    }
}
