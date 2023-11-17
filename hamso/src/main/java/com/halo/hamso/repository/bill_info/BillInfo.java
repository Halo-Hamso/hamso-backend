package com.halo.hamso.repository.bill_info;

import com.halo.hamso.repository.account_book.AccountBook;
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

    private String itemType;

    private Integer cost;

    private Integer count;

    private LocalDateTime useTime;

    @JoinColumn(name = "accountBookId")
    @ManyToOne(fetch = FetchType.EAGER)
    private AccountBook accountBook;
}
