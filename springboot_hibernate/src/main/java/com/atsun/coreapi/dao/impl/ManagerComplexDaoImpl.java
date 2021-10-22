package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.ManagerComplexDao;
import com.atsun.coreapi.enums.ManagerType;
import com.atsun.coreapi.po.BaseModel;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.vo.ManagerVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ManagerComplexDaoImpl extends ComplexDaoImpl<Manager, String> implements ManagerComplexDao {

    @Override
    public Manager get(String username) {

        String where = "WHERE o.username=:username ";

        Map<String, Object> params = new HashMap<>(3);

        params.put("username", username);

        return super.getSingleResult(where, params);
    }

    @Override
    public Manager getSingleById(String id) {

        String hql = String.format("FROM %s o WHERE o.id=:id ", getClassName());

        Map<String, Object> params = new HashMap<>(3);

        params.put("id", id);

        return super.getSingleResultByHql(hql, params);
    }

    @Override
    public ManagerVO getOneById(String id) {

        String sql = String.format("SELECT o.id AS id, o.username AS username, o.real_name AS realName, o.type AS type " +
                "FROM %s o WHERE o.id=:id ", getTableName());

        Map<String, Object> params = new HashMap<>(5);

        params.put("id", id);

        return super.getSingleResultBySql(sql, params, ManagerVO.class);
    }

    @Override
    public PageBean<ManagerVO> getPage(String username, Page page) {

        String sql = String.format("SELECT o.id AS id, o.username AS username, o.real_name AS realName, o.type AS type " +
                "FROM %s o WHERE 1=1 ", getTableName());

        Map<String, Object> params = new HashMap<>(5);

        if (StringUtils.isNotBlank(username)) {
            sql += "AND o.username LIKE :username ";
            params.put("username", username);
        }

        return super.getPageBySql(sql, params, BaseModel.DEFAULT_CREATE_DATETIME_SQL_DESC_ORDERS, page, ManagerVO.class);
    }

    @Override
    public PageBean<ManagerVO> getPage(String username, ManagerType type, Page page) {

        String hql = String.format("SELECT o.id AS id, o.username AS username, o.realName AS realName, o.type AS type " +
                "FROM %s o WHERE 1=1 ", getTableName());

        Map<String, Object> params = new HashMap<>(5);

        if (StringUtils.isNotBlank(username)) {
            hql += "AND o.username LIKE :username ";
            params.put("username", username);
        }

        if (null != type) {
            hql += "AND o.type=:type ";
            params.put("type", type);
        }

        return super.getPageByHql(hql, params, BaseModel.DEFAULT_CREATE_DATETIME_DESC_ORDERS, page, ManagerVO.class);
    }

}
