package cn.me.service.impl;


import cn.me.dao.IWorkmanageDao;
import cn.me.domain.Workbill;
import cn.me.domain.Workordermanage;
import cn.me.service.IWorkmanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkmanageServiceImpl implements IWorkmanageService{
    @Autowired
    private IWorkmanageDao workmanageDao;
    @Override
    public void save(Workordermanage model) {
        workmanageDao.save(model);
    }
}
