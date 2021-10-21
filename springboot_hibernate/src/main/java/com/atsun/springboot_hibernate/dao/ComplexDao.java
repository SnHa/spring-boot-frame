package com.atsun.springboot_hibernate.dao;

import com.atsun.springboot_hibernate.bean.Page;
import com.atsun.springboot_hibernate.bean.PageBean;
import com.atsun.springboot_hibernate.enums.Direction;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Description: Created by LD on 2018/6/26</p>
 *
 * @author LD
 */
public interface ComplexDao<T, ID extends Serializable> {

    long getTotal();

    long getTotal(String where, Map<String, Object> params);

    long getTotalByHql(String hql, Map<String, Object> params);

    long getTotalBySql(String sql, Map<String, Object> params);

    boolean existsTable(String tableSchema, String tableName);

    boolean exists(String where, Map<String, Object> params);

    boolean existsByHql(String hql, Map<String, Object> params);

    boolean existsBySql(String sql, Map<String, Object> params);

    int executeHql(String hql, Map<String, Object> params);

    int executeSql(String sql, Map<String, Object> params);

    int del(String where, Map<String, Object> params);

    int delById(ID id);

    int delByIds(List<ID> ids);

    T getSingleResult(String where, Map<String, Object> params);

    T getSingleResultByHql(String hql, Map<String, Object> params);

    <K> K getSingleResultByHql(String hql, Map<String, Object> params, Class<K> resultClass);

    <K> K getSingleResultBySql(String sql, Map<String, Object> params, Class<K> resultClass);

    T getFirstResult(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders);

    <K> K getFirstResultByHql(String hql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Class<K> resultClass);

    <K> K getFirstResultBySql(String sql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Class<K> resultClass);

    List<T> getList(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders);

    List<ID> getListIds(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders);

    List<T> getListByHql(String hql, Map<String, Object> params, LinkedHashMap<String, Direction> orders);

    <K> List<K> getListByHql(String hql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Class<K> resultClass);

    List<T> getListBySql(String sql, Map<String, Object> params, LinkedHashMap<String, Direction> orders);

    <K> List<K> getListBySql(String sql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Class<K> resultClass);

    List<T> getPageList(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page);

    List<ID> getPageListIds(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page);

    <K> List<K> getPageListByHql(String hql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass);

    <K> List<K> getPageListBySql(String sql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass);

    PageBean<T> getPage(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page);

    PageBean<ID> getPageIds(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page);

    <K> PageBean<K> getPageByHql(String hql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass);

    <K> PageBean<K> getPageByHql(String hql, String totalHql, String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass);

    <K> PageBean<K> getPageBySql(String sql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass);

    <K> PageBean<K> getPageBySql(String sql, String totalSql, String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass);

}
