package cn.me.web.action;


import cn.me.domain.Workordermanage;
import cn.me.service.IWorkmanageService;
import cn.me.web.action.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class WorkmanageAction extends BaseAction<Workordermanage> {
    @Autowired
    private IWorkmanageService workmanageService;
    public String add() throws Exception {
        String f = "1";
        try {
            workmanageService.save(model);
        } catch (Exception e) {
            e.printStackTrace();
            f = "0";
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(f);
        return NONE;
    }
}
