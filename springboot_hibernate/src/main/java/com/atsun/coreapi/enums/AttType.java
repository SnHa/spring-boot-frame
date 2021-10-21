package com.atsun.coreapi.enums;

import lombok.Getter;

@Getter
public enum AttType implements EnumFormat<String> {

    HEAD_IMAGE("HEAD_IMAGE", "头像", 0),
    BANNER("BANNER", "横幅", 1),
    ACTIVITY("ACTIVITY", "活动", 2),
    CLIENT_VERSION("CLIENT_VERSION", "客户端版本", 3);

    private final String tag;

    private final String name;

    private final int orderNum;

    AttType(String tag, String name, int orderNum) {
        this.tag = tag;
        this.name = name;
        this.orderNum = orderNum;
    }

    public static AttType getEnum(String tag) {
        try {
            return valueOf(tag.toUpperCase());
        } catch (Exception ignore) {
        }

        return null;
    }

}
