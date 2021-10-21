package com.atsun.springboot_hibernate.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Description: Created by LD on 2020/02/01</p>
 *
 * @author LD
 */
@Getter
@Setter
@NoArgsConstructor
public class PageBean<T> extends Page {

    private static final long serialVersionUID = 5262054157291817345L;

    private int pageElements = 0;

    private int totalPages = 0;

    private long totalElements = 0L;

    private boolean hasNext = false;

    private boolean hasPrevious = false;

    private List<T> records = new ArrayList<>();

    public PageBean(Page page) {
        super(page);
    }

    public PageBean<T> loadData(Long totalElements, List<T> records) {
        this.totalElements = totalElements;
        this.records = records;
        this.pageElements = null == this.records ? 0 : records.size();
        this.totalPages = 0 == super.getPageSize() ? 1 : (int) Math.ceil((double) this.totalElements / (double) super.getPageSize());
        this.hasNext = super.getPageNumber() < this.totalPages;
        this.hasPrevious = super.getPageNumber() > DEFAULT_START_PAGE_NUMBER;
        return this;
    }

}
