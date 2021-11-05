package com.atsun.coreapi.dto;

import lombok.Data;

/**
 * @author SH
 */
@Data
public class MenuPageDTO extends PageDTO {

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单名称
     */
    private String name;
}
