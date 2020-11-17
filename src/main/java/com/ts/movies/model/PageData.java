package com.ts.movies.model;

import java.util.List;

public class PageData<T> {
    private List<T> data;
    private long totalCount;

    public PageData(List<T> data, long totalCount) {
        this.data = data;
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PageData{" +
                "data=" + data +
                ", totalCount=" + totalCount +
                '}';
    }
}
