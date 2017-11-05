package cn.me.service.impl;

import cn.me.dao.IStaffDao;
import cn.me.domain.Staff;
import cn.me.service.IStaffService;
import cn.me.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private IStaffDao staffDao;


    public String add(Staff model) {
        staffDao.save(model);
        return null;
    }

    public void queryPage(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    public void edit(Staff model) {
        Staff staff = staffDao.findById(model.getId());
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setDeltag(model.getDeltag());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());
        staff.setHaspda(model.getHaspda());
        staffDao.update(staff);
    }

    public void delete(String ids) {
        if(StringUtils.isNotBlank(ids)){
            System.out.println(ids);
            String[] staffIds = ids.split(",");
            for (String id: staffIds
                 ) {
                staffDao.execUpdate("staff.delete",id);
            }
        }
    }
    public void restore(String ids) {
        if(StringUtils.isNotBlank(ids)){
            System.out.println(ids);
            String[] staffIds = ids.split(",");
            for (String id: staffIds
                    ) {
                staffDao.execUpdate("staff.restore",id);
            }
        }
    }

    public List<Staff> findListNotDel() {
        DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
        dc.add(Restrictions.eq("deltag","0"));
        List<Staff> staffList = staffDao.findByCriteria(dc);
        return staffList;
    }

}
