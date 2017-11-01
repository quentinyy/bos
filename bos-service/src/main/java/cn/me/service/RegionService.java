package cn.me.service;

import cn.me.domain.Region;
import cn.me.utils.PageBean;

import java.util.List;

public interface RegionService {
    void saveBeatch(List<Region> regionList);

    void queryPage(PageBean pageBean);
}
