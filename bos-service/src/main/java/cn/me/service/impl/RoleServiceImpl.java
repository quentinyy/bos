package cn.me.service.impl;

import cn.me.dao.IFunctionDao;
import cn.me.dao.IRoleDao;
import cn.me.domain.Function;
import cn.me.domain.Role;
import cn.me.service.IRoleService;
import cn.me.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements IRoleService{
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IFunctionDao functionDao;
    @Override
    public void save(Role model, String functionIds) {
        roleDao.save(model);
        System.out.println();
        if(StringUtils.isNotBlank(functionIds)){
            String[] fIds = functionIds.split(",");
            for (String functionId:fIds
                 ) {
                Function function = functionDao.findById(functionId);
                model.getFunctions().add(function);
            }
        }
    }

    @Override
    public void queryPage(PageBean pageBean) {
        roleDao.pageQuery(pageBean);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
