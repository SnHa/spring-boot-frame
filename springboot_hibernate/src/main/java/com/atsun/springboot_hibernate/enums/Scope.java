package com.atsun.springboot_hibernate.enums;

import lombok.Getter;

@Getter
public enum Scope implements EnumFormat<String> {

    PLATFORM("PLATFORM", "平台", 0);

    private final String tag;

    private final String name;

    private final int orderNum;

    Scope(String tag, String name, int orderNum) {
        this.tag = tag;
        this.name = name;
        this.orderNum = orderNum;
    }

    public static Scope getEnum(String tag) {
        try {
            return valueOf(tag.toUpperCase());
        } catch (Exception ignore) {
        }

        return PLATFORM;
    }

}
