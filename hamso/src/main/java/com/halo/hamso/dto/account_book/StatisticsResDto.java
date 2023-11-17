package com.halo.hamso.dto.account_book;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResDto {
    List<VisitedToCntResDto> visitedToMoney;
    List<FamilyCntResDto> familyNameMoney;
}
