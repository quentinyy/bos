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
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        pageBean.setDetachedCriteria(detachedCriteria);
        staffService.queryPage(pageBean);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
        String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().write(json);
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
