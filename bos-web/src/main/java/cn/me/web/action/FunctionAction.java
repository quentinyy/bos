package cn.me.web.action;

import cn.me.domain.Function;
import cn.me.service.IFunctionService;
import cn.me.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function>{
    @Autowired
    private IFunctionService functionService;
    public String ajaxlist() throws Exception {

        List<Function> list = functionService.ajaxlist();
        java2json(list,new String[]{"parentFunction","roles"});
        return NONE;
    }

    public String add() throws Exception {
        functionService.save(model);
        return LIST;
    }
    public String queryPage() throws Exception {

        functionService.queryPage(pageBean);
        java2json(pageBean,new String[]{"parentFunction","children","roles"});
        return NONE;
    }
}
