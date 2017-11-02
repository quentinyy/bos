package cn.me.service;

import cn.me.domain.Subarea;
import cn.me.utils.PageBean;

import java.util.List;

public interface SubareaService {
    void add(Subarea model);

    void queryPage(PageBean pageBean);

    List<Subarea> findAll();
}
