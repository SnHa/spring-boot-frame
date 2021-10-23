package com.atsun.coreapi.dao;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.enums.ManagerType;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.vo.ManagerVO;

public interface ManagerComplexDao extends ComplexDao<Manager, String> {

    Manager get(String username);

    Manager getSingleById(String id);

    ManagerVO getUserSql(String username);

    ManagerVO getOneById(String id);

    PageBean<ManagerVO> getPage(String username, Page page);

    PageBean<ManagerVO> getPage(String username, ManagerType type, Page page);

}
