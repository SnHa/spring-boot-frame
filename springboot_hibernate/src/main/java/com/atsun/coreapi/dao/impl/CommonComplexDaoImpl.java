package com.atsun.coreapi.dao.impl;

import com.atsun.coreapi.dao.CommonComplexDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CommonComplexDaoImpl<T, ID extends Serializable> extends ComplexDaoImpl<T, ID> implements CommonComplexDao<T, ID> {

    @Override
    public boolean existsCode(String code, ID exceptId) {

        String where = "WHERE o.code=:code ";

        Map<String, Object> params = new HashMap<>(3);

        params.put("code", code);

        if (null != exceptId) {
            where += "AND o.id!=:exceptId ";
            params.put("exceptId", exceptId);
        }

        return super.exists(where, params);
    }

    @Override
    public boolean existsName(String name, ID exceptId) {

        String where = "WHERE o.name=:name ";

        Map<String, Object> params = new HashMap<>(3);

        params.put("name", name);

        if (null != exceptId) {
            where += "AND o.id!=:exceptId ";
            params.put("exceptId", exceptId);
        }

        return super.exists(where, params);
    }

}
