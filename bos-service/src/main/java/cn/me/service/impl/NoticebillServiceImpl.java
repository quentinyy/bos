package cn.me.service.impl;

import cn.me.crm.ICustomerDao;
import cn.me.crm.domain.Customer;
import cn.me.dao.IDecidezoneDao;
import cn.me.dao.INoticebillDao;
import cn.me.dao.IWorkbillDao;
import cn.me.domain.*;
import cn.me.service.INoticebillService;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;


@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService{
    @Autowired
    private ICustomerDao customerDao;
    @Autowired
    private INoticebillDao noticebillDao;
    @Autowired
    private IDecidezoneDao decidezoneDao;
    @Autowired
    private IWorkbillDao workbillDao;
    @Override
    public Customer findCustomerByTelephone(String telephone) {
        Customer customer = customerDao.findCustomerByTelephone(telephone);
        return customer;
    }

    @Override
    public void save(Noticebill model) {
        model.setUser((User) ActionContext.getContext().getSession().get("loginUser"));
        noticebillDao.save(model);
        String pickaddress = model.getPickaddress();
        String decidedzoneId = customerDao.findDecidedzoneByAdress(pickaddress);
        if(decidedzoneId!=null){
            Decidedzone decidedzone = decidezoneDao.findById(decidedzoneId);
            Staff staff = decidedzone.getStaff();
            model.setStaff(staff);
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);

            Workbill workbill = new Workbill();
            workbill.setAttachbilltimes(0);//追单次数
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//创建时间，当前系统时间
            workbill.setNoticebill(model);//工单关联页面通知单
            workbill.setPickstate(Workbill.PICKSTATE_NO);//取件状态
            workbill.setRemark(model.getRemark());//备注信息
            workbill.setStaff(staff);//工单关联取派员
            workbill.setType(Workbill.TYPE_1);//工单类型
            workbillDao.save(workbill);
        }else {
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }
    }
}
