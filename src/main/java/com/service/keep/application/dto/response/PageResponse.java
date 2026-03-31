/**
 * author @bhupendrasambare
 * Date   :31/03/26
 * Time   :1:07 am
 * Project:Keep
 **/
package com.service.keep.application.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T> {

    private List<T> content;
    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PageResponse(List<T> content, Page<?> pageData) {
        this.content = content;
        this.page = pageData.getNumber();
        this.pageSize = pageData.getSize();
        this.totalElements = pageData.getTotalElements();
        this.totalPages = pageData.getTotalPages();
        this.last = pageData.isLast();
    }

    public List<T> getContent() { return content; }
    public int getPage() { return page; }
    public int getPageSize() { return pageSize; }
    public long getTotalElements() { return totalElements; }
    public int getTotalPages() { return totalPages; }
    public boolean isLast() { return last; }
}
