package com.atsun.coreapi.service;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.vo.ManagerVO;
import org.apache.shiro.authz.AuthorizationInfo;

import java.util.List;

public interface ManagerService {
    /**
     * 获取用户
     *
     * @param username
     * @return
     */
    ManagerVO getUser(String username);

    /**
     * 获取所有的用户
     *
     * @param page
     * @return
     */
    List<ManagerVO> getAllManager(Page page);

    /**
     * 添加用户
     *
     * @param manager
     * @return
     */
    Boolean addManager(ManagerDTO manager);

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    boolean deleteManager(String id);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 根据id查询到数据进行修改
     *
     * @param managerDTO
     * @return
     */
    Boolean update(ManagerDTO managerDTO);

    /**
     * 根据不定参数查询数据
     *
     * @param managerDTO
     * @return
     */
    List<ManagerVO> conditionSelect(ManagerDTO managerDTO);

    /**
     * 根据id查询授权信息
     *
     * @param id
     * @return
     */
    AuthorizationInfo authorizationInfo(String id);
}
