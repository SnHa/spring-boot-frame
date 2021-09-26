package com.atsun.demo.service.impl;

import com.atsun.demo.utils.Result;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.atsun.demo.entity.User;
import com.atsun.demo.mapper.UserMapper;
import com.atsun.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public int selectByName(User user) {
     String username=user.getUsername();
     String password=user.getPassword();
     User  user1 =userMapper.selectName(username);
        //判断用户是否存在
        if (user1!=null){
            //判断密码是匹
            if (password.equals(user1.getPassword())){
                return 1;
            }
        }
        return 0;
    }

}
