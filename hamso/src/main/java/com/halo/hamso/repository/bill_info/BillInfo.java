package com.halo.hamso.repository.bill_info;

import com.halo.hamso.repository.account_book.AccountBook;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class BillInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemType;

    @Column(nullable = false)
    private Integer cost;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private LocalDateTime useTime;

    @JoinColumn(name = "accountBookId")
    @ManyToOne(fetch = FetchType.EAGER)
    private AccountBook accountBook;

    @Builder
    public BillInfo(String itemType, Integer cost, Integer count, LocalDateTime useTime, AccountBook accountBook) {
        this.itemType = itemType;
        this.cost = cost;
        this.count = count;
        this.useTime = useTime;
        this.accountBook = accountBook;
    }
}
