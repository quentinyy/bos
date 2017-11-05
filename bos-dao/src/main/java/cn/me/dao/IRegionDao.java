package cn.me.dao;

import cn.me.dao.base.BaseDao;
import cn.me.domain.Region;

import java.util.List;

public interface IRegionDao extends BaseDao<Region>{
    List<Region> findByQ(String q);
}
