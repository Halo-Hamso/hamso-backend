package com.halo.hamso.dto.account_book;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FamilyCntResDto {
    private String familyName;
    private Long money;

    public FamilyCntResDto(String familyName, Long money) {
        this.familyName = familyName;
        this.money = money;
    }
}
