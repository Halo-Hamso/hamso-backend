package com.halo.hamso.dto.account_book;

import com.halo.hamso.repository.bill_info.BillInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BillInfoByUseTime {
    private String itemType;
    private Integer count;
    private Integer cost;
    private LocalDateTime useTime;

    public BillInfoByUseTime(BillInfo billInfo) {
        this.itemType = billInfo.getItemType();
        this.count = billInfo.getCount();
        this.cost = billInfo.getCost();
        this.useTime = billInfo.getUseTime();
    }
}
