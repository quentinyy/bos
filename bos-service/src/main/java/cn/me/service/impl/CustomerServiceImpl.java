package cn.me.service.impl;

import cn.me.crm.ICustomerDao;
import cn.me.crm.domain.Customer;
import cn.me.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService{
    @Autowired
    private ICustomerDao customerDao;
    @Override
    public List<Customer> findCustomerAssociate() {
        List<Customer> list = customerDao.findCustomerAssociate();
        return list;
    }

    @Override
    public List<Customer> findCustomerNotAssociate() {
        List<Customer> list = customerDao.findCustomerNotAssociate();
        return list;
    }
}
