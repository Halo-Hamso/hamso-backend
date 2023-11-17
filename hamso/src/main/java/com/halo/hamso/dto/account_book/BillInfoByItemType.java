package com.halo.hamso.dto.account_book;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BillInfoByItemType {
    private String itemType;
    private Long count;
    private Long cost;

    public BillInfoByItemType(String itemType, Long count, Long cost) {
        this.itemType = itemType;
        this.count = count;
        this.cost = cost;
    }
}
