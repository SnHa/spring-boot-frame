package com.atsun.coreapi.service;

import com.atsun.coreapi.vo.ManagerVO;

public interface ManagerService {
    /**
     * 获取用户
     * @param username
     * @return
     */
    ManagerVO getUser(String username);
}
