package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.Role;
import org.springframework.stereotype.Repository;

/**
 * @author HP
 */
@Repository
public interface RoleSimpleDao extends SimpleDao<Role, String>, RoleComplexDao {
}
