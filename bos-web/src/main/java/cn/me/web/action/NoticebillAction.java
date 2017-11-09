package cn.me.web.action;

import cn.me.crm.domain.Customer;
import cn.me.domain.Noticebill;
import cn.me.service.INoticebillService;
import cn.me.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill>{
    @Autowired
    private INoticebillService ns;

    public String findCustomerByTelephone() throws Exception {
        Customer customer = ns.findCustomerByTelephone(model.getTelephone());
        java2json(customer,new String[]{});
        return NONE;
    }

    public String add() throws Exception {
        ns.save(model);
        return "noticebill_add";
    }
}
