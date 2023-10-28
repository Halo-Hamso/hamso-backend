package com.halo.hamso.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PageInfo {
    private int page;
    private int pageSize;
    private Long totalNumber;
    private int totalPages;
}
