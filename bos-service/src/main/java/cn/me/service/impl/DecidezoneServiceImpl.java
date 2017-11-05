package cn.me.service.impl;

import cn.me.dao.IDecidezoneDao;
import cn.me.dao.ISubareaDao;
import cn.me.domain.Decidedzone;
import cn.me.domain.Subarea;
import cn.me.service.IDecidezoneService;
import cn.me.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DecidezoneServiceImpl implements IDecidezoneService {
    @Autowired
    private IDecidezoneDao DecidezoneDao;
    @Autowired
    private ISubareaDao subareaDao;
    public void save(Decidedzone model, String[] subareaid) {
            DecidezoneDao.save(model);
        for (String id:subareaid
             ) {
            Subarea subarea = subareaDao.findById(id);
            subarea.setDecidedzone(model);

        }
    }

    public void queryPage(PageBean pageBean) {
        DecidezoneDao.pageQuery(pageBean);
    }
}
