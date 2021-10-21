package com.atsun.coreapi.enums;

import lombok.Getter;

@Getter
public enum ManagerType implements EnumFormat<String> {

    SUPER("SUPER", "超级管理员"),
    SYSTEM("SYSTEM", "系统管理员");

    private final String tag;

    private final String name;

    private int orderNum;

    ManagerType(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }

}
