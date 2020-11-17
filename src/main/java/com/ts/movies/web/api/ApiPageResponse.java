package com.ts.movies.web.api;

import java.util.List;

public class ApiPageResponse<T> {
    private final List<T> data;
    private final long totalRecords;

    public ApiPageResponse(List<T> data, long totalRecords) {
        this.data = data;
        this.totalRecords = totalRecords;
    }

    public List<T> getData() {
        return data;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    @Override
    public String toString() {
        return "ApiPageResponse{" +
                "data=" + data +
                ", totalRecords=" + totalRecords +
                '}';
    }
}
