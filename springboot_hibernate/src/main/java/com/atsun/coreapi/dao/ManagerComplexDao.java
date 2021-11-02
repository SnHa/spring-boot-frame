package com.atsun.coreapi.dao;

import com.atsun.coreapi.bean.Page;
import com.atsun.coreapi.bean.PageBean;
import com.atsun.coreapi.dto.ManagerDTO;
import com.atsun.coreapi.enums.ManagerType;
import com.atsun.coreapi.po.Manager;
import com.atsun.coreapi.vo.ManagerVO;

import java.util.List;

/**
 * @author SH
 */
public interface ManagerComplexDao extends ComplexDao<Manager, String> {

    /**
     * 通过用户名查询用户数据
     *
     * @param username
     * @return 用户实体类
     */
    Manager get(String username);

    /**
     * 根据id查询单条用户数据
     *
     * @param id
     * @return 用户实体类
     */
    Manager getSingleById(String id);

    /**
     * 根据名字查询单个用户
     *
     * @param username
     * @return 返回用户VO类
     */
    ManagerVO getUserSql(String username);

    /**
     * 根据id获取查询单个用户
     *
     * @param id
     * @return 返回用户VO类
     */
    ManagerVO getOneById(String id);

    /**
     * 判断是否有用户名传入，如果有进行用户名的模糊查询，如果没有就查询全部，分页
     *
     * @param username
     * @param page
     * @return
     */
    PageBean<ManagerVO> getPage(String username, Page page);

    /**
     * (hql)
     * 判断是否有用户名传入，如果有进行用户名的模糊查询，如果没有就查询全部，分页
     *
     * @param username
     * @param page
     * @param type
     * @return
     */
    PageBean<ManagerVO> getPage(String username, ManagerType type, Page page);

    /**
     * 获取用户的全部数据
     *
     * @param page
     * @return
     */
    List<ManagerVO> getAll(Page page);

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return
     */
    String getName(String username);

    /**
     * 条件查询
     * @param managerDTO
     * @return
     */
    List<ManagerVO> getPageManager(ManagerDTO managerDTO);
}
