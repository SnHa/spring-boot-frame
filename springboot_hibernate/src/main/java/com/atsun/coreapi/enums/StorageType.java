package com.atsun.coreapi.enums;

import lombok.Getter;

/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Description: Created by LD on 2018/7/9</p>
 *
 * @author LD
 */
@Getter
public enum StorageType implements EnumFormat<String> {

    ALI_OSS("ALI_OSS", "阿里OSS", 0),
    MIN_IO("MIN_IO", "MIN_IO", 1);

    private final String tag;

    private final String name;

    private final int orderNum;

    StorageType(String tag, String name, int orderNum) {
        this.tag = tag;
        this.name = name;
        this.orderNum = orderNum;
    }

    public static StorageType getEnum(String tag) {
        try {
            return valueOf(tag.toUpperCase());
        } catch (Exception ignore) {
        }

        return ALI_OSS;
    }

}
