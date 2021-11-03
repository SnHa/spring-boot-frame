package com.atsun.coreapi.service;

import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.dto.ManagerPageDTO;
import com.atsun.coreapi.exception.TransException;
import com.atsun.coreapi.vo.ManagerVO;
import org.apache.shiro.authz.AuthorizationInfo;

public interface ManagerService {

    /**
     * 获取用户
     *
     * @param username
     * @return
     */
    ManagerVO get(String username);

    /**
     * 添加用户
     *
     * @param dto
     * @return
     */
    void edit(ManagerDTO dto) throws TransException;

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    void delete(String id) throws TransException;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password) throws TransException;

    /**
     * 根据不定参数查询数据
     *
     * @param dto
     * @return
     */
    PageBean<ManagerVO> getPage(ManagerPageDTO dto) throws TransException;

    /**
     * 根据id查询授权信息
     *
     * @param id
     * @return
     */
    AuthorizationInfo authorizationInfo(String id);

}
