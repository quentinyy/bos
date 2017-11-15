package cn.me.web.action;

import cn.me.domain.Role;
import cn.me.service.IRoleService;
import cn.me.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
    @Autowired
    private IRoleService roleService;
    private String functionIds;

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }


    public String add() throws Exception {
        roleService.save(model,functionIds);
        return LIST;
    }

    public String queryPage() throws Exception {
        roleService.queryPage(pageBean);
        java2json(pageBean,new String[]{"functions","users"});
        return NONE;
    }

    public String ajaxlist() throws Exception {
        List<Role> list =  roleService.findAll();
        java2json(list,new String[]{"functions","users"});
        return NONE;
    }
}
