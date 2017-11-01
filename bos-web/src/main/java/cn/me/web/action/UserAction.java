package cn.me.web.action;

import cn.me.domain.User;
import cn.me.service.UserService;
import cn.me.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Autowired
    private UserService userService;

    public String login() throws Exception {
        if(checkcode==null | checkcode ==""){
            addActionError("请输入验证码");
            System.out.println(checkcode);
            return LOGIN;
        }else if (!checkcode.equals((String) ActionContext.getContext().getSession().get("key"))){
            addActionError("验证码错误");
            return LOGIN;
        }
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
