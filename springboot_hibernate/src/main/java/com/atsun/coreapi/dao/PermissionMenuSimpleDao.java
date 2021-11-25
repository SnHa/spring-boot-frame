package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.PermissionMenu;
import org.springframework.stereotype.Repository;

/**
 * @author SH
 */
@Repository
public interface PermissionMenuSimpleDao extends SimpleDao<PermissionMenu, String>, PermissionMenuSimpleComplexDao {

}
