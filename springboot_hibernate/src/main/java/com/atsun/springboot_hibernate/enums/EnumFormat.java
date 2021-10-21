package com.atsun.springboot_hibernate.enums;

public interface EnumFormat<T> {

    T getTag();

    String getName();

    int getOrderNum();

}
