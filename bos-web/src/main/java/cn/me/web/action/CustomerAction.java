package cn.me.web.action;

import cn.me.crm.ICustomerDao;
import cn.me.crm.domain.Customer;
import cn.me.service.ICustomerService;
import cn.me.web.action.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class CustomerAction extends BaseAction<Customer>{
    @Autowired
    private ICustomerService customerService;

    public String findCustomerAssociate() throws Exception {

        List<Customer> list = customerService.findCustomerAssociate();
        java2json(list,new String[]{""});
        return NONE;
    }
    public String findCustomerNotAssociate() throws Exception {

        List<Customer> list = customerService.findCustomerNotAssociate();
        java2json(list,new String[]{""});
        return NONE;
    }
}
