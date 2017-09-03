package com.tubugs.springboot.dto;

import java.util.List;

/**
 * Created by xuzhang on 2017/7/10.
 */
public class PagingDto<T> {

    private int page_index;
    private int page_size;
    private long total;
    private List<T> list;

    public PagingDto() {
    }

    public PagingDto(int page_index, int page_size, long total, List<T> list) {
        this.page_index = page_index;
        this.page_size = page_size;
        this.total = total;
        this.list = list;
    }

    public int getPage_index() {
        return page_index;
    }

    public void setPage_index(int page_index) {
        this.page_index = page_index;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
