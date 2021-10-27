package com.atsun.coreapi.service;

import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.vo.ManagerVO;

import java.util.List;

public interface ManagerService {
    /**
     * 获取用户
     * @param username
     * @return
     */
    ManagerVO getUser(String username);

    /**
     * 获取所有的用户
     * @return
     */
    List<ManagerVO> getAllManager();

    /**
     * 添加用户
     * @param manager
     * @return
     */
    Manager addManager(ManagerDTO manager);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    boolean deleteManager(String id);
}
