package cn.me.web.action;

import cn.me.crm.ICustomerDao;
import cn.me.domain.Function;
import cn.me.domain.User;
import cn.me.service.IUserService;
import cn.me.utils.MD5Utils;
import cn.me.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Autowired
    private IUserService userService;
    public String login() throws Exception {
        if(checkcode==null | checkcode ==""){
            addActionError("请输入验证码");
            System.out.println(checkcode);
            return LOGIN;
        }else if (!checkcode.equals((String) ActionContext.getContext().getSession().get("key"))){
            addActionError("验证码错误");
            return LOGIN;
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),MD5Utils.md5(model.getPassword()));
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return LOGIN;
        }

        User user = (User) subject.getPrincipal();
        if(user != null){
            ActionContext.getContext().getSession().put("loginUser",user);
            return HOME;
        }
        addActionError("用户名或密码错误");
        return LOGIN;
    }

    public String loginOut() throws Exception {
        ActionContext.getContext().getSession().remove("loginUser");
        return LOGIN;
    }
    public String editPwd() throws Exception {
        User user = (User) ActionContext.getContext().getSession().get("loginUser");
        String f="1";
        try {
            String pwd = MD5Utils.md5(model.getPassword());
            userService.editPwd(pwd,user.getId());

        } catch (Exception e) {
            e.printStackTrace();
            f="0";
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().write(f);
        return NONE;
    }

    private String[] roleIds;

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public String add() throws Exception {
        userService.save(model,roleIds);
        return LIST;
    }
    public String queryPage()throws Exception{
        userService.queryPage(pageBean);
        java2json(pageBean,new String[]{"noticebills","roles"});
        return NONE;
    }

}
