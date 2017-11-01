package cn.me.service.impl;

import cn.me.dao.StaffDao;
import cn.me.domain.Staff;
import cn.me.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{
    @Autowired
    private StaffDao staffDao;


    public String add(Staff model) {
        staffDao.save(model);
        return null;
    }
}
