package com.halo.hamso.repository.account_book;


import com.halo.hamso.common.AuditingField;
import com.halo.hamso.repository.account_info.AccountInfo;
import com.halo.hamso.repository.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Integer totalMoney;


    @OneToOne(mappedBy = "accountBook")
    private Member member;

    @OneToMany(mappedBy = "accountBook", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();

    public void setMember(Member member) {
        this.member = member;
    }

}
