package cn.me.service;

import cn.me.domain.Staff;
import cn.me.utils.PageBean;

public interface StaffService {

    String add(Staff model);

    void queryPage(PageBean pageBean);

    void edit(Staff model);

    void delete(String ids);

    void restore(String ids);
}
