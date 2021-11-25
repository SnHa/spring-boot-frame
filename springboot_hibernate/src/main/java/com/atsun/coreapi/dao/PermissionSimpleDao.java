package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.Permission;
import org.springframework.stereotype.Repository;

/**
 * @author SH
 */
@Repository
public interface PermissionSimpleDao extends SimpleDao<Permission,String>,PermissionComplexDao{
}
