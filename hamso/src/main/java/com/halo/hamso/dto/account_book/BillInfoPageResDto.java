package com.halo.hamso.dto.account_book;


import com.halo.hamso.dto.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillInfoPageResDto {
    private List<?> billInfos;
    private PageInfo pageInfo;
}
