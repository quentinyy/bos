package cn.me.web.action.base;

import cn.me.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
    public static final String HOME = "home";
    public static final String LIST = "list";
    protected PageBean pageBean = new PageBean();
    public void setPage(int page) {
        this.pageBean.setCurrentPage(page);
    }

    public void setRows(int rows) {
        this.pageBean.setPageSize(rows);
    }
    DetachedCriteria detachedCriteria = null;
    protected T model;
    public T getModel() {
        return model;
    }
    public BaseAction(){
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        detachedCriteria = DetachedCriteria.forClass(entityClass);
        pageBean.setDetachedCriteria(detachedCriteria);
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void java2json(Object obj,String[] exclude){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclude);
        String json = JSONObject.fromObject(obj,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        try {
            ServletActionContext.getResponse().getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void java2json(List obj, String[] exclude){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclude);
        String json = JSONArray.fromObject(obj,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        try {
            ServletActionContext.getResponse().getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
