package com.halo.hamso.dto.chart;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HourIntervalMoneyInfo {
    private String date;
    private Long money;

    public HourIntervalMoneyInfo(String date, Long money) {
        this.date = date;
        this.money = money;
    }
}
