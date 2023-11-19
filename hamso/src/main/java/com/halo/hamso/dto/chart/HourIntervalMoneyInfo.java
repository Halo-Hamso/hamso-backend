package com.halo.hamso.dto.chart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HourIntervalMoneyInfo {
    private String date;
    private Long money;

    public HourIntervalMoneyInfo(String date, Long money) {
        this.date = date;
        this.money = money;
    }
}
