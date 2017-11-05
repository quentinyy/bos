package cn.me.service;

import cn.me.domain.Decidedzone;
import cn.me.utils.PageBean;

import java.util.List;

public interface IDecidezoneService {
    void save(Decidedzone model, String[] subareaid);

    void queryPage(PageBean pageBean);

    void associate(String id, String[] customerIds);
}
