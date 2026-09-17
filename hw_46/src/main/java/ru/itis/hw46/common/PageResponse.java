package ru.itis.hw46.common;

import java.util.List;

public class PageResponse<T> {

    private final List<T> items;
    private final int page;
    private final int size;
    private final int totalPages;
    private final long totalElements;

    public PageResponse(List<T> items, int page, int size, int totalPages, long totalElements) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<T> getItems() {
        return items;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
