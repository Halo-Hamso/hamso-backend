package com.halo.hamso.dto.account_book;


import com.halo.hamso.dto.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class AccountInfoPageResDto {
    private PageInfo pageInfo;
    private List<AccountInfoResDto> accountList;
}
