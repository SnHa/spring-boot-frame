package com.atsun.api.enums;

import lombok.Getter;

@Getter
public enum PermissionType implements EnumFormat<String> {

    MENU("MENU", "菜单", 0),
    BUTTON("BUTTON", "按钮", 1);

    private final String tag;

    private final String name;

    private final int orderNum;

    PermissionType(String tag, String name, int order) {
        this.tag = tag;
        this.name = name;
        this.orderNum = order;
    }

}
