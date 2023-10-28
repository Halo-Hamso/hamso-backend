package com.halo.hamso.repository.account_info;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.halo.hamso.repository.account_book.AccountBook;
import com.halo.hamso.repository.member.Member;
import lombok.AllArgsConstructor;
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

    @Column(nullable = false)
    private Integer money;

    @JoinColumn(name = "accountBookId")
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountBook accountBook;
}
