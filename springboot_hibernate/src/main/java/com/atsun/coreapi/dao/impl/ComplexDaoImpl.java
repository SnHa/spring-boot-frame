package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dao.ComplexDao;
import com.atsun.coreapi.enums.Direction;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.query.internal.QueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Description: Created by LD on 2018/6/26</p>
 *
 * @author LD
 */
@Repository
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ComplexDaoImpl<T, ID extends Serializable> implements ComplexDao<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    protected Class<T> getClazz() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    protected Class<ID> getIdClazz() {
        return (Class<ID>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected String getTableName() {
        return getTableName(getClazz());
    }

    protected String getTableName(Class<?> clazz) {

        Table table = clazz.getAnnotation(Table.class);

        return null == table ? getClassName() : table.name();
    }

    protected String getClassName() {
        return getClassName(getClazz());
    }

    protected String getClassName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

    protected boolean isEntityBean(Class<?> clazz) {
        return null != clazz && (null != clazz.getAnnotation(Table.class) || null != clazz.getAnnotation(Entity.class));
    }

    protected boolean isEntityBean() {
        return isEntityBean(getClazz());
    }

    protected String returnLikeContaining(String value) {
        return String.format("%s%s%s", "%", value, "%");
    }

    protected String returnLikeStartingWith(String value) {
        return String.format("%s%s", value, "%");
    }

    protected String returnLikeEndingWith(String value) {
        return String.format("%s%s", "%", value);
    }

    protected String createOrder(LinkedHashMap<String, Direction> orders) {

        if (null == orders || orders.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder(" ORDER BY ");

        for (String key : orders.keySet()) {
            sb.append(key).append(" ").append(orders.get(key).toString()).append(", ");
        }

        return sb.deleteCharAt(sb.lastIndexOf(",")).toString();
    }

    protected LinkedHashMap<String, Direction> mergeToQueryFieldsOrders(LinkedHashMap<String, String> fieldsOrders, Map<String, String> fieldsMapping) {

        if (null == fieldsOrders || fieldsOrders.isEmpty() || null == fieldsMapping || fieldsMapping.isEmpty()) {
            return null;
        }

        return fieldsOrders.keySet().stream()
                .filter(fieldsMapping::containsKey)
                .collect(Collectors.toMap(fieldsMapping::get, key -> Direction.fromString(fieldsOrders.get(key)), (a, b) -> b, LinkedHashMap::new));
    }

    protected final List<Class<?>> IGNORE_ALIAS_TO_BEAN_CLASSES = new ArrayList<Class<?>>() {

        private static final long serialVersionUID = 2702592180589462580L;

        {
            add(String.class);
            add(BigDecimal.class);
            add(Long.class);
            add(Integer.class);
            add(Double.class);
            add(Float.class);
            add(Short.class);
            add(Boolean.class);
            add(Date.class);
        }
    };

    protected <K> Query createHqlQuery(String hql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass) {

        hql = String.format("%s %s", hql, createOrder(orders));

        boolean entityBean = isEntityBean(resultClass);
        boolean ignoreAliasToBean = IGNORE_ALIAS_TO_BEAN_CLASSES.contains(resultClass);

        Query q = entityBean || ignoreAliasToBean ? entityManager.createQuery(hql, resultClass) : entityManager.createQuery(hql);

        setQuery(q, params, page);

        if (null != resultClass && !entityBean && !ignoreAliasToBean) {
            q.unwrap(QueryImpl.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        }

        return q;
    }

    protected <K> Query createNativeQuery(String sql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass) {

        sql = String.format("%s %s", sql, createOrder(orders));

        Query q = entityManager.createNativeQuery(sql);

        if (null != params && !params.isEmpty()) {
            params.forEach((k, v) -> params.put(k, v instanceof Boolean ? ((boolean) v ? "Y" : "N") : v instanceof Enum ? v.toString() : v));
        }

        setQuery(q, params, page);

        boolean ignoreAliasToBean = IGNORE_ALIAS_TO_BEAN_CLASSES.contains(resultClass);

        if (null != resultClass && !ignoreAliasToBean) {
            q.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        }

        return q;
    }

    protected void setQuery(Query q, Map<String, Object> params, Page page) {

        if (null != params && !params.isEmpty()) {
            params.forEach(q::setParameter);
        }

        if (null != page && page.getPageSize() > 0) {
            q.setFirstResult((page.getPageNumber() - 1) * page.getPageSize());
            q.setMaxResults(page.getPageSize());
        }
    }

    @Override
    public long getTotal() {
        return getTotal(null, null);
    }

    @Override
    public long getTotal(String where, Map<String, Object> params) {
        return getTotalByHql(String.format("SELECT COUNT(1) FROM %s o %s", getClassName(), StringUtils.defaultString(where)), params);
    }

    @Override
    public long getTotalByHql(String hql, Map<String, Object> params) {
        return (Long) createHqlQuery(hql, params, null, null, null).getSingleResult();
    }

    @Override
    public long getTotalBySql(String sql, Map<String, Object> params) {
        return (Long) createNativeQuery(sql, params, null, null, null).getSingleResult();
    }

    @Override
    public boolean existsTable(String tableSchema, String tableName) {

        String sql = "SELECT COUNT(1) FROM information_schema.tables WHERE table_schema=:tableSchema AND table_name=:tableName";

        Map<String, Object> params = new HashMap<>(5);

        params.put("tableSchema", tableSchema);
        params.put("tableName", tableName);

        return getTotalBySql(sql, params) > 0;
    }

    @Override
    public boolean exists(String where, Map<String, Object> params) {
        return getTotal(where, params) > 0;
    }

    @Override
    public boolean existsByHql(String hql, Map<String, Object> params) {
        return getTotalByHql(hql, params) > 0;
    }

    @Override
    public boolean existsBySql(String sql, Map<String, Object> params) {
        return getTotalBySql(sql, params) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int executeHql(String hql, Map<String, Object> params) {
        return createHqlQuery(hql, params, null, null, null).executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int executeSql(String sql, Map<String, Object> params) {
        return createNativeQuery(sql, params, null, null, null).executeUpdate();
    }

    @Override
    public int del(String where, Map<String, Object> params) {
        return executeHql(String.format("DELETE FROM %s o %s", getClassName(), StringUtils.defaultString(where)), params);
    }

    @Override
    public int delById(ID id) {

        Map<String, Object> params = new HashMap<>(4);

        params.put("id", id);

        return del("WHERE o.id=:id", params);
    }

    @Override
    public int delByIds(List<ID> ids) {

        Map<String, Object> params = new HashMap<>(4);

        params.put("ids", ids);

        return del("WHERE o.id IN (:ids)", params);
    }

    @Override
    public T getSingleResult(String where, Map<String, Object> params) {
        return getSingleResultByHql(String.format("SELECT o FROM %s o %s", getClassName(), StringUtils.defaultString(where)), params, getClazz());
    }

    @Override
    public T getSingleResultByHql(String hql, Map<String, Object> params) {
        try {
            return getSingleResultByHql(hql, params, getClazz());
        } catch (EmptyResultDataAccessException | NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K> K getSingleResultByHql(String hql, Map<String, Object> params, Class<K> resultClass) {
        try {
            return (K) createHqlQuery(hql, params, null, null, resultClass).getSingleResult();
        } catch (EmptyResultDataAccessException | NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K> K getSingleResultBySql(String sql, Map<String, Object> params, Class<K> resultClass) {
        try {
            return (K) createNativeQuery(sql, params, null, null, resultClass).getSingleResult();
        } catch (EmptyResultDataAccessException | NoResultException e) {
            return null;
        }
    }

    @Override
    public T getFirstResult(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders) {
        return getFirstResultByHql(String.format("SELECT o FROM %s o %s", getClassName(), StringUtils.defaultString(where)), params, orders, getClazz());
    }

    @Override
    public <K> K getFirstResultByHql(String hql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Class<K> resultClass) {

        List<K> list = getPageListByHql(hql, params, orders, new Page(1), resultClass);

        return null != list && !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public <K> K getFirstResultBySql(String sql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Class<K> resultClass) {

        List<K> list = getPageListBySql(sql, params, orders, new Page(1), resultClass);

        return null != list && !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public List<T> getList(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders) {
        return getPageList(where, params, orders, null);
    }

    @Override
    public List<ID> getListIds(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders) {
        return getPageListIds(where, params, orders, null);
    }

    @Override
    public List<T> getListByHql(String hql, Map<String, Object> params, LinkedHashMap<String, Direction> orders) {
        return getPageListByHql(hql, params, orders, null, getClazz());
    }

    @Override
    public <K> List<K> getListByHql(String hql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Class<K> resultClass) {
        return getPageListByHql(hql, params, orders, null, resultClass);
    }

    @Override
    public List<T> getListBySql(String sql, Map<String, Object> params, LinkedHashMap<String, Direction> orders) {
        return getListBySql(sql, params, orders, getClazz());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K> List<K> getListBySql(String sql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Class<K> resultClass) {
        return createNativeQuery(sql, params, orders, null, resultClass).getResultList();
    }

    @Override
    public List<T> getPageList(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page) {
        return getPageListByHql(String.format("SELECT o FROM %s o %s", getClassName(), StringUtils.defaultString(where)), params, orders, page, getClazz());
    }

    @Override
    public List<ID> getPageListIds(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page) {
        return getPageListByHql(String.format("SELECT o.id FROM %s o %s", getClassName(), StringUtils.defaultString(where)), params, orders, page, getIdClazz());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K> List<K> getPageListByHql(String hql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass) {
        return createHqlQuery(hql, params, orders, page, resultClass).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K> List<K> getPageListBySql(String sql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass) {
        return createNativeQuery(sql, params, orders, page, resultClass).getResultList();
    }

    @Override
    public PageBean<T> getPage(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page) {

        PageBean<T> pageBean = new PageBean<>(null == page ? new Page() : page);

        return pageBean.loadData(getTotal(where, params), getPageList(where, params, orders, page));
    }

    @Override
    public PageBean<ID> getPageIds(String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page) {

        PageBean<ID> pageBean = new PageBean<>(null == page ? new Page() : page);

        return pageBean.loadData(getTotal(where, params), getPageListIds(where, params, orders, page));
    }

    @Override
    public <K> PageBean<K> getPageByHql(String hql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass) {
        return getPageByHql(hql, null, null, params, orders, page, resultClass);
    }

    @Override
    public <K> PageBean<K> getPageByHql(String hql, String totalHql, String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass) {

        PageBean<K> pageBean = new PageBean<>(null == page ? new Page() : page);

        hql = String.format("%s %s", hql, StringUtils.defaultString(where));
        totalHql = StringUtils.isBlank(totalHql) ? String.format("SELECT COUNT(1) %s", hql.substring(hql.toUpperCase().indexOf("FROM"))) : String.format("%s %s", totalHql, StringUtils.defaultString(where));

        return pageBean.loadData(getTotalByHql(totalHql, params), getPageListByHql(hql, params, orders, page, resultClass));
    }

    @Override
    public <K> PageBean<K> getPageBySql(String sql, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass) {
        return getPageBySql(sql, null, null, params, orders, page, resultClass);
    }

    @Override
    public <K> PageBean<K> getPageBySql(String sql, String totalSql, String where, Map<String, Object> params, LinkedHashMap<String, Direction> orders, Page page, Class<K> resultClass) {

        PageBean<K> pageBean = new PageBean<>(null == page ? new Page() : page);

        sql = String.format("%s %s", sql, StringUtils.defaultString(where));
        totalSql = StringUtils.isBlank(totalSql) ? String.format("SELECT COUNT(1) %s", sql.substring(sql.toUpperCase().indexOf("FROM"))) : String.format("%s %s", totalSql, StringUtils.defaultString(where));

        return pageBean.loadData(getTotalBySql(totalSql, params), getPageListBySql(sql, params, orders, page, resultClass));
    }

}
