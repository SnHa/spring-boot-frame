package com.atsun.coreapi.dao;

import com.atsun.coreapi.po.Menu;
import org.springframework.stereotype.Repository;

/**
 * @author SH
 */
@Repository
public interface MenuSimpleDao extends SimpleDao<Menu, String>, MenuComplexDao {
}
