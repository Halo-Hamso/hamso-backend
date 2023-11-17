package com.halo.hamso.dto.chart;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AllHourIntervalResDto {
    List<HourIntervalMoneyInfo> profits;
    List<HourIntervalMoneyInfo> costs;

    @Builder
    public AllHourIntervalResDto(List<HourIntervalMoneyInfo> profits, List<HourIntervalMoneyInfo> costs) {
        this.profits = profits;
        this.costs = costs;
    }
}
