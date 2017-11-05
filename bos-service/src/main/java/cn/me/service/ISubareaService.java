package cn.me.service;

import cn.me.domain.Subarea;
import cn.me.utils.PageBean;

import java.util.List;

public interface ISubareaService {
    void add(Subarea model);

    void queryPage(PageBean pageBean);

    List<Subarea> findAll();

    List<Subarea> findSubareaNotAssion();

    List<Subarea> findSubareaAssociate(String id);
}
