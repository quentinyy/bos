package cn.me.web.action;

import cn.me.domain.User;
import cn.me.service.UserService;
import cn.me.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

    @Autowired
    private UserService userService;

    public String login() throws Exception {
        User user = userService.login(model);
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
}
