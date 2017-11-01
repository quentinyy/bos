package cn.me.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import static com.opensymphony.xwork2.Action.LOGIN;

public class LoginInterceptor extends MethodFilterInterceptor{
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        if(ActionContext.getContext().getSession().get("loginUser")==null){
            return LOGIN;
        }
        return actionInvocation.invoke();
    }
}
