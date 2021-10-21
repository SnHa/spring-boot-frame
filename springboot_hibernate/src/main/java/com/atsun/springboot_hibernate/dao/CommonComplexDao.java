package com.atsun.springboot_hibernate.dao;

import java.io.Serializable;

public interface CommonComplexDao<T, ID extends Serializable> extends ComplexDao<T, ID> {

    /**
     * 查询是否存在，通过code
     *
     * @param code     编码
     * @param exceptId 排除id
     * @return 返回是否存在
     */
    boolean existsCode(String code, ID exceptId);

    /**
     * 查询是否存在，通过名称
     *
     * @param name     名称
     * @param exceptId 排除id
     * @return 返回是否存在
     */
    boolean existsName(String name, ID exceptId);

}
