package com.atsun.coreapi.service;

import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.dto.ManagerPageDTO;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.vo.ManagerVO;
import org.apache.shiro.authz.AuthorizationInfo;

/**
 * @author SH
 */
public interface ManagerService {

    /**
     * 获取用户
     *
     * @param username 用户名
     * @return ManagerVO
     */
    ManagerVO get(String username);

    /**
     * 添加用户
     *
     * @param dto ManagerDTO
     * @throws TransException 异常处理
     */
    void edit(ManagerDTO dto) throws TransException;

    /**
     * 删除用户信息
     *
     * @param managerId 用户id
     * @throws TransException 异常处理
     */
    void delete(String managerId) throws TransException;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return String
     * @throws TransException 异常处理
     */
    String login(String username, String password) throws TransException;

    /**
     * 根据不定参数查询数据
     *
     * @param dto ManagerPageDTO
     * @return ManagerVO
     * @throws TransException 异常处理
     */
    PageBean<ManagerVO> getPage(ManagerPageDTO dto) throws TransException;

    /**
     * 根据id查询授权信息
     *
     * @param id 用户id
     * @return AuthorizationInfo
     */
    AuthorizationInfo authorizationInfo(String id);

}
