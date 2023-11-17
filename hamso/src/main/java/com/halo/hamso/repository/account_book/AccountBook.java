package com.halo.hamso.repository.account_book;


import com.halo.hamso.common.AuditingField;
import com.halo.hamso.repository.account_info.AccountInfo;
import com.halo.hamso.repository.bill_info.BillInfo;
import com.halo.hamso.repository.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class AccountBook extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer totalProfit;

    @Column(nullable = false)
    private Integer totalCost;


    @JoinColumn(name = "memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "accountBook", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();

    @OneToMany(mappedBy = "accountBook", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BillInfo> billInfos = new ArrayList<BillInfo>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost + this.totalCost;
    }

    public void setTotalProfit(Integer totalProfit) {
        this.totalProfit = totalProfit + this.totalProfit;
    }
}
