package cn.me.web.action;

import cn.me.domain.Staff;
import cn.me.service.StaffService;
import cn.me.utils.PageBean;
import cn.me.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
    @Autowired
    private StaffService staffService;

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
        java2json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize"});
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
}
