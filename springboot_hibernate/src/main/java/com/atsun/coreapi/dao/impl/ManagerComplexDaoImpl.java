package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.ManagerComplexDao;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.enums.ManagerType;
import com.atsun.coreapi.po.BaseModel;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.vo.ManagerVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HP
 */
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
    public ManagerVO getUserSql(String username) {

        String sql = String.format("SELECT o.id AS id, o.username AS username, o.real_name AS realName, o.type AS type , o.sexual AS sexual, o.password AS password, o.state AS state " +
                "FROM %s o WHERE o.username=:username ", getTableName());

        Map<String, Object> params = new HashMap<>(5);

        params.put("username", username);

        return super.getSingleResultBySql(sql, params, ManagerVO.class);
    }

    @Override
    public ManagerVO getOneById(String id) {

        String sql = String.format("SELECT o.id AS id, o.username AS username, o.real_name AS realName, o.type AS type, o.sexual AS sexual, o.head_image_att_id AS headImageAttId " +
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

    @Override
    public List<ManagerVO> getAll(Page page) {

        String sql = "SELECT o.id AS id, o.username AS username, o.real_name AS realName, o.last_login_datetime AS lastLoginDatetime, " +
                " o.type AS type, o.state AS state FROM t_manager o ";

        return super.getPageListBySql(sql, null, null, page, ManagerVO.class);
    }

    @Override
    public String getName(String username) {
        String sql = "SELECT o.username  FROM t_manager o WHERE username LIKE :username";

        HashMap<String, Object> params = new HashMap<>(5);
        params.put("username", username);

        return super.getSingleResultBySql(sql, params, String.class);
    }

    @Override
    public List<ManagerVO> getPageManager(ManagerDTO managerDTO) {

        String sql = "SELECT o.id AS id, o.username AS username, o.real_name AS realName, o.last_login_datetime AS lastLoginDatetime," +
                " o.type AS type, o.state AS state  FROM t_manager o WHERE ";

        Page page = new Page();
        page.setPageNumber(managerDTO.getPage());
        page.setPageSize(managerDTO.getSize());

        HashMap<String, Object> params = new HashMap<>(5);

        if (managerDTO.getUsername() != null) {
            params.put("username", "%"+managerDTO.getUsername()+"%");
            sql += " o.username LIKE :username ";
        }
        if (managerDTO.getState() != null) {
            params.put("state", managerDTO.getState());
            sql += "AND o.state LIKE :state  ";
        }

        return super.getPageListBySql(sql, params, null, page, ManagerVO.class);
    }


}
