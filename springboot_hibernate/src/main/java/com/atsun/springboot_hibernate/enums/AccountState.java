package com.atsun.springboot_hibernate.enums;

import lombok.Getter;

@Getter
public enum AccountState implements EnumFormat<String> {

    NORMAL("NORMAL", "正常", 0),
    LOCKED("LOCKED", "锁定", 1);

    private final String tag;

    private final String name;

    private final int orderNum;

    AccountState(String tag, String name, int orderNum) {
        this.tag = tag;
        this.name = name;
        this.orderNum = orderNum;
    }

    public static AccountState getEnum(String tag) {
        try {
            return valueOf(tag.toUpperCase());
        } catch (Exception ignore) {
        }

        return LOCKED;
    }

}
