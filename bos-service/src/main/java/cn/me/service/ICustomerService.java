package cn.me.service;

import cn.me.crm.domain.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findCustomerAssociate(Customer model);

    List<Customer> findCustomerNotAssociate();
}
