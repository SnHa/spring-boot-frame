package com.atsun.demo.service;

import com.atsun.demo.entity.User;
public interface UserService{


    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /*用户登录*/
    int selectByName(User user);
}
