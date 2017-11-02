package cn.me.service.impl;

import cn.me.dao.DecidezoneDao;
import cn.me.dao.SubareaDao;
import cn.me.domain.Decidedzone;
import cn.me.domain.Subarea;
import cn.me.service.DecidezoneService;
import cn.me.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DecidezoneServiceImpl implements DecidezoneService{
    @Autowired
    private DecidezoneDao decidezoneDao;
    @Autowired
    private SubareaDao subareaDao;
    public void save(Decidedzone model, String[] subareaid) {
            decidezoneDao.save(model);
        for (String id:subareaid
             ) {
            Subarea subarea = subareaDao.findById(id);
            subarea.setDecidedzone(model);

        }
    }

    public void queryPage(PageBean pageBean) {
        decidezoneDao.pageQuery(pageBean);
    }
}
