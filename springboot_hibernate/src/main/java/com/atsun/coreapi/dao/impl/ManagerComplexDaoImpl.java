package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.ManagerComplexDao;
import com.atsun.coreapi.enums.AccountState;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.vo.ManagerVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HP
 */
@Repository
public class ManagerComplexDaoImpl extends ComplexDaoImpl<Manager, String> implements ManagerComplexDao {

    @Override
    public ManagerVO get(String username) {

        String sql = String.format("SELECT o.id AS id, o.username AS username, o.real_name AS realName, o.type AS type , o.sexual AS sexual, o.password AS password, o.state AS state " +
                "FROM %s o WHERE o.username=:username ", getTableName());

        Map<String, Object> params = new HashMap<>(5);

        params.put("username", username);

        return super.getSingleResultBySql(sql, params, ManagerVO.class);
    }

    @Override
    public String getUsername(String username, String exceptId) {

        String sql = "SELECT o.username FROM t_manager o WHERE username=:username ";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("username", username);

        if (StringUtils.isNotBlank(exceptId)) {
            sql += "AND o.id!=:exceptId ";
            params.put("exceptId", exceptId);
        }

        return super.getSingleResultBySql(sql, params, String.class);
    }

    @Override
    public PageBean<ManagerVO> getPage(String username, AccountState state, Page page) {

        String sql = "SELECT o.id AS id, o.username AS username, o.real_name AS realName, o.last_login_datetime AS lastLoginDatetime, " +
                "o.type AS type, o.state AS state FROM t_manager o WHERE 1=1 ";

        HashMap<String, Object> params = new HashMap<>(5);

        if (StringUtils.isNotBlank(username)) {
            sql += "AND o.username LIKE :username ";
            params.put("username", returnLikeContaining(username));
        }

        if (null != state) {
            sql += "AND o.state=:state ";
            params.put("state", state);
        }

        return super.getPageBySql(sql, params, null, page, ManagerVO.class);
    }

}
