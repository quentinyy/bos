package cn.me.service;

import cn.me.crm.domain.Customer;
import cn.me.domain.Noticebill;

public interface INoticebillService {
    public Customer findCustomerByTelephone(String telephone);

    void save(Noticebill model);
}
