package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.RoleComplexDao;
import com.atsun.coreapi.po.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleSimpleDaoImpl extends ComplexDaoImpl<Role, String> implements RoleComplexDao {
}
