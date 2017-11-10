package cn.me.web.action;

import cn.me.domain.Staff;
import cn.me.service.IStaffService;
import cn.me.web.action.base.BaseAction;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
    @Autowired
    private IStaffService staffService;

    public String add() throws Exception {
        String row = staffService.add(model);
        return LIST;
    }
    private int page;
    private int rows;

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String queryPage() throws Exception {

        staffService.queryPage(pageBean);
        java2json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","decidedzones"});
        return NONE;
    }

    public String edit() throws Exception {
        staffService.edit(model);
        return LIST;
    }
    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }
    @RequiresPermissions("staff.delete")
    public String delete() throws Exception {
        System.out.println(ids);
        staffService.delete(ids);
        return LIST;
    }

    public String restore() throws Exception {
        System.out.println(ids);
        staffService.restore(ids);
        return LIST;
    }

    public String ajaxlist() throws Exception {
        List<Staff> list = staffService.findListNotDel();
        java2json(list,new String[]{"decidedzones"});
        return NONE;
    }
}
