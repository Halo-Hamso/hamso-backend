package com.halo.hamso.dto.account_book;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountInfoReqDto {
    private Long memberId;
    private String name;
    private String team;
    private String visitedTo;
    private String relation;
    private Integer money;
}
