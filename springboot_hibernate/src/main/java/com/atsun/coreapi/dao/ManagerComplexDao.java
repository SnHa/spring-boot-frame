package com.atsun.coreapi.dao;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.enums.AccountState;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.vo.ManagerVO;

/**
 * @author SH
 */
public interface ManagerComplexDao extends ComplexDao<Manager, String> {

    /**
     * 根据名字查询单个用户
     *
     * @param username
     * @return 返回用户VO类
     */
    ManagerVO get(String username);

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return
     */
    String getUsername(String username, String exceptId);

    /**
     * 分页条件
     */
    PageBean<ManagerVO> getPage(String username, AccountState state, Page page);

}
