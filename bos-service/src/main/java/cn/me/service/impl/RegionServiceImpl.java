package cn.me.service.impl;

import cn.me.dao.RegionDao;
import cn.me.domain.Region;
import cn.me.service.RegionService;
import cn.me.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RegionServiceImpl implements RegionService{
    @Autowired
    private RegionDao regionDao;
    public void saveBeatch(List<Region> regionList) {
        for (Region region:regionList
             ) {
            regionDao.saveOrUpdate(region);
        }
    }

    public void queryPage(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    public List<Region> findAll() {
        List<Region> list = regionDao.findAll();
        return list;
    }

    public List<Region> findByQ(String q) {
        List<Region> regionList = regionDao.findByQ(q);
        return regionList;
    }
}
