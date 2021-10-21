package com.atsun.coreapi.enums;

import lombok.Getter;

@Getter
public enum Sexual implements EnumFormat<String> {

    MALE("MALE", "男", 0),
    FEMALE("FEMALE", "女", 1);

    private final String tag;

    private final String name;

    private final int orderNum;

    Sexual(String tag, String name, int orderNum) {
        this.tag = tag;
        this.name = name;
        this.orderNum = orderNum;
    }

    public static Sexual getEnum(String tag) {
        try {
            return valueOf(tag.toUpperCase());
        } catch (Exception ignore) {
        }

        return MALE;
    }

}
