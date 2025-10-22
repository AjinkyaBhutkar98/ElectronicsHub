package com.ajinkyabhutkar.electronicstore.dtos;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageableResponse<T> {

    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean isLast;

    public PageableResponse() {
    }

    public PageableResponse(List<T> content, boolean isLast, int totalPages, long totalElements, int size, int page) {
        this.content = content;
        this.isLast = isLast;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.page = page;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public static <T> PageableResponse<T> fromPage(Page<T> page) {
        return new PageableResponse<>(
                page.getContent(),
                page.isLast(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber()
        );
    }

}
