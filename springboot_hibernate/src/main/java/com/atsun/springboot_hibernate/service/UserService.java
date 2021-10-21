package com.atsun.springboot_hibernate.service;

import com.atsun.springboot_hibernate.entity.User;

/**
 * @author SH
 */
public interface UserService {
    /**
     * 插入用户数据
     * @param user
     * @return
     */
    User insert(User user);

    /**
     * 查询用户
     * @param id
     * @return
     */
    User select(Integer id);
}
