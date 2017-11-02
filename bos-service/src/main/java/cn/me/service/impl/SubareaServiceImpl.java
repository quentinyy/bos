package cn.me.service.impl;

import cn.me.dao.SubareaDao;
import cn.me.domain.Subarea;
import cn.me.service.SubareaService;
import cn.me.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService{
    @Autowired
    private SubareaDao subareaDao;

    public void add(Subarea model) {
        subareaDao.save(model);
    }

    public void queryPage(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    public List<Subarea> findAll() {
        return subareaDao.findAll();
    }

    public List<Subarea> findSubareaNotAssion() {
        DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
        dc.add(Restrictions.isNull("decidedzone"));
        List<Subarea> list = subareaDao.findByCriteria(dc);

        return list;
    }
}
