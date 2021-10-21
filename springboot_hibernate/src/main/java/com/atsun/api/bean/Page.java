package com.atsun.api.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Description: Created by LD on 2020/02/01</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
public class Page implements Serializable {

    private static final long serialVersionUID = -5484620246950957159L;

    public static final int DEFAULT_START_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_MAX_LIMIT_PAGE_SIZE = 200;

    private int pageNumber = DEFAULT_START_PAGE_NUMBER;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private LinkedHashMap<String, Sort.Direction> orders = new LinkedHashMap<>();

    public Page(Page page) {
        this(page.getPageNumber(), page.getPageSize(), page.getOrders());
    }

    public Page(int pageSize) {
        this(DEFAULT_START_PAGE_NUMBER, pageSize);
    }

    public Page(int pageNumber, int pageSize) {
        this(pageNumber, pageSize, null);
    }

    public Page(int pageNumber, int pageSize, LinkedHashMap<String, Sort.Direction> orders) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.orders = orders;
    }

    public void first() {
        this.pageNumber = DEFAULT_START_PAGE_NUMBER;
    }

    public void next() {
        this.pageNumber += 1;
    }

    public void previous() {
        this.pageNumber = this.pageNumber <= DEFAULT_START_PAGE_NUMBER ? DEFAULT_START_PAGE_NUMBER : this.pageNumber - 1;
    }

    public void putOrder(String field, Sort.Direction direction) {
        this.orders = null == this.orders ? new LinkedHashMap<>() : this.orders;
        this.orders.putIfAbsent(field, direction);
    }

    public LinkedHashMap<String, String> simpleOrders() {

        LinkedHashMap<String, String> simpleOrders = new LinkedHashMap<>();

        if (null == this.orders || this.orders.isEmpty()) {
            return simpleOrders;
        }

        this.orders.forEach((key, val) -> simpleOrders.put(key, val.toString()));

        return simpleOrders;
    }

    public static Page ofLimitPage() {
        return of(DEFAULT_START_PAGE_NUMBER, DEFAULT_MAX_LIMIT_PAGE_SIZE);
    }

    public static Page of(int pageNumber, int pageSize) {
        return of(pageNumber, pageSize, null);
    }

    public static Page of(Page page) {
        return of(page.getPageNumber(), page.getPageSize(), page.getOrders());
    }

    public static Page of(int pageNumber, int pageSize, LinkedHashMap<String, Sort.Direction> orders) {
        return new Page(pageNumber, pageSize, orders);
    }

    public static class Sort {

        public enum Direction {

            ASC, DESC;

            public static Sort.Direction fromString(String value) {
                try {
                    return valueOf(value.toUpperCase());
                } catch (Exception e) {
                    throw new IllegalArgumentException(String.format("Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
                }
            }

            public static Sort.Direction fromStringOrNull(String value) {
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
    }

}
