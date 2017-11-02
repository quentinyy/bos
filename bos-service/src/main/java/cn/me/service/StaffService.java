package cn.me.service;

import cn.me.domain.Staff;
import cn.me.utils.PageBean;

import java.util.List;

public interface StaffService {

    String add(Staff model);

    void queryPage(PageBean pageBean);

    void edit(Staff model);

    void delete(String ids);

    void restore(String ids);

    List<Staff> findListNotDel();
}
