package cn.me.realm;

import cn.me.dao.IFunctionDao;
import cn.me.dao.IUserDao;
import cn.me.domain.Function;
import cn.me.domain.User;
import cn.me.service.IUserService;
import cn.me.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BosRealm extends AuthorizingRealm{
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IFunctionDao functionDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Function> list = functionDao.findFunctionByUserId(user.getId());
        for (Function f:list
             ) {
            info.addStringPermission(f.getCode());
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userDao.findUserByUsername(username);
        if(user==null){
            return null;
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),this.getName());
        return info;
    }
}
