package com.halo.hamso.dto.account_book;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VisitedToCntResDto {
    private String visitedTo;
    private Long money;

    public VisitedToCntResDto(String visitedTo, Long money) {
        this.visitedTo = visitedTo;
        this.money = money;
    }
}
