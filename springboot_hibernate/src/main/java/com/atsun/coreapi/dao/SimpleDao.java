package com.atsun.coreapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Description: Created by LD on 2018/6/25</p>
 *
 * @author LD
 */
@NoRepositoryBean
public interface SimpleDao<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
