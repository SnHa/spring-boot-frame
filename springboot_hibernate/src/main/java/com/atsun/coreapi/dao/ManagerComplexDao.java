package com.atsun.coreapi.dao;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.enums.AccountState;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.vo.ManagerVO;
import org.springframework.stereotype.Repository;

/**
 * @author SH
 */
@Repository
public interface ManagerComplexDao extends ComplexDao<Manager, String> {

    /**
     * 根据名字查询单个用户
     *
     * @param username 用户名
     * @return 返回用户VO类
     */
    ManagerVO get(String username);

    /**
     * 查询用户名是否存在
     *
     * @param username 用户名
     * @param exceptId id
     * @return String
     */
    String getUsername(String username, String exceptId);

    /**
     * 分页条件
     *
     * @param username 用户名
     * @param state    状态
     * @param page     分页
     * @return PageBean<ManagerVO>
     * @throws TransException 异常
     */
    PageBean<ManagerVO> getPage(String username, AccountState state, Page page) throws TransException;

}
