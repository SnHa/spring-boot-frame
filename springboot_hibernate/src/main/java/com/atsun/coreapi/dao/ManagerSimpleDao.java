package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.Manager;
import org.springframework.stereotype.Repository;

/**
 * @author SH
 */
@Repository
public interface ManagerSimpleDao extends SimpleDao<Manager, String>, ManagerComplexDao {
}
