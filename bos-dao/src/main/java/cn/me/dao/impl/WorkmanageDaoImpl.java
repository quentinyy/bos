package cn.me.dao.impl;

import cn.me.dao.IWorkmanageDao;
import cn.me.dao.base.impl.BaseDaoImpl;
import cn.me.domain.Workbill;
import cn.me.domain.Workordermanage;
import org.springframework.stereotype.Repository;

@Repository
public class WorkmanageDaoImpl extends BaseDaoImpl<Workordermanage> implements IWorkmanageDao{
}
