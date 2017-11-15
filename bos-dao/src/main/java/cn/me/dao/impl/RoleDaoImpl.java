package cn.me.dao.impl;

import cn.me.dao.IRoleDao;
import cn.me.dao.base.impl.BaseDaoImpl;
import cn.me.domain.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {
}
