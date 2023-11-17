package com.halo.hamso.repository.account_info;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.halo.hamso.repository.account_book.AccountBook;
import com.halo.hamso.repository.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class AccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String visitedTo;

    @Column(nullable = false)
    private String team;

    @Column(nullable = false)
    private String relation;

    private String familyName;

    @Column(nullable = false)
    private Integer money;

    @JoinColumn(name = "accountBookId")
    @ManyToOne(fetch = FetchType.EAGER)
    private AccountBook accountBook;

    @Builder
    public AccountInfo(String name, String visitedTo, String team, String relation, Integer money, AccountBook accountBook) {
        this.name = name;
        this.visitedTo = visitedTo;
        this.team = team;
        this.relation = relation;
        this.money = money;
        this.accountBook = accountBook;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
