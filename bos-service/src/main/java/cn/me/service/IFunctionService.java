package cn.me.service;

import cn.me.domain.Function;
import cn.me.utils.PageBean;

import java.util.List;


public interface IFunctionService {
     void queryPage(PageBean pageBean);

    void save(Function model);

    List<Function> ajaxlist( );

    List<Function> findMenu(String id);
}
