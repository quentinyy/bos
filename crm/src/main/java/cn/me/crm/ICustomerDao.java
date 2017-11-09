package cn.me.crm;

import cn.me.crm.domain.Customer;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface ICustomerDao {
    List<Customer> findCustomerNotAssociate();
    List<Customer> findCustomerAssociate(String decidedzzone_id);
    void associate(String id, String[] customerIds);
    Customer findCustomerByTelephone(String telephone);
    String findDecidedzoneByAdress(String adress);
}
