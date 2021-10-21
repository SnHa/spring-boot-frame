package com.atsun.coreapi.enums;

import org.apache.commons.lang3.StringUtils;

public enum Direction {

    ASC, DESC;

    public static Direction fromString(String value) {
        try {
            return valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
        }
    }

    public static Direction fromStringOrNull(String value) {
        try {
            return StringUtils.isBlank(value) ? Direction.ASC : fromString(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public boolean isAscending() {
        return this.equals(ASC);
    }

    public boolean isDescending() {
        return this.equals(DESC);
    }

}
