package cn.me.service.impl;

import cn.me.dao.IFunctionDao;
import cn.me.domain.Function;
import cn.me.service.IFunctionService;
import cn.me.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService{
    @Autowired
    private IFunctionDao functionDao;
    @Override
    public void queryPage(PageBean pageBean) {
        functionDao.pageQuery(pageBean);
    }

    @Override
    public void save(Function model) {
        Function parentFunction = model.getParentFunction();
        if(parentFunction!=null && parentFunction.getId().equals("")){
            model.setParentFunction(null);
        }
        functionDao.save(model);
    }

    @Override
    public List<Function> ajaxlist( ) {
        List<Function> list = functionDao.findAll();
        return list;
    }

    @Override
    public List<Function> findMenu(String id) {
        List<Function> list = functionDao.findMenu(id);
        return list;
    }


}
