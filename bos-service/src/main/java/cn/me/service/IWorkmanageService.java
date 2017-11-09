package cn.me.service;

import cn.me.domain.Workbill;
import cn.me.domain.Workordermanage;
import org.springframework.stereotype.Service;


public interface IWorkmanageService {
    void save(Workordermanage model);
}
