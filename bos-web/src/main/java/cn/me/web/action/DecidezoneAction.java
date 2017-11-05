package cn.me.web.action;

import cn.me.domain.Decidedzone;
import cn.me.service.IDecidezoneService;
import cn.me.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope("prototype")
public class DecidezoneAction extends BaseAction<Decidedzone>{
    @Autowired
    private IDecidezoneService decidezoneService;

    private String[] subareaid;

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    public String add() throws Exception {
        decidezoneService.save(model,subareaid);
        return LIST;
    }

    public String queryPage() throws Exception {
        decidezoneService.queryPage(pageBean);
        java2json(pageBean,new String[]{"currentPage","detachedCriteria",
                "pageSize","subareas","decidedzones"});
        return NONE;
    }

    private String[] customerIds;

    public void setCustomerIds(String[] customerIds) {
        this.customerIds = customerIds;
    }

    public String associate() throws Exception {
        decidezoneService.associate(model.getId(),customerIds);
        return LIST;
    }
}
