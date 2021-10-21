package com.atsun.api.enums;

import lombok.Getter;

@Getter
public enum AttOrigin implements EnumFormat<String> {

    CLIENT("CLIENT", "客户端"),
    ADMIN("ADMIN", "管理端");

    private final String tag;

    private final String name;

    private int orderNum;

    AttOrigin(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    public static AttOrigin getEnum(String tag) {
        try {
            return valueOf(tag.toUpperCase());
        } catch (Exception ignore) {
        }

        return CLIENT;
    }

}
