package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.ManagerRole;
import org.springframework.stereotype.Repository;

/**
 * @author HP
 */
@Repository
public interface ManagerRoleSimpleDao extends SimpleDao<ManagerRole, String>, ManagerRoleComplexDao {
}
