package com.halo.hamso.repository.member;



import com.halo.hamso.repository.Authority.Authority;
import com.halo.hamso.repository.account_book.AccountBook;
import com.halo.hamso.repository.funeral.Funeral;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNo;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String relation;


    @OneToMany(mappedBy = "member")
    private List<Funeral> funeralList = new ArrayList<Funeral>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountBookId")
    private AccountBook accountBook;


    @Builder
    public Member(String name, String phoneNo, String password, String relation) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.password = password;
        this.relation = relation;
    }


    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Authority> roles = new ArrayList<>();


    public void setRoles(List<Authority> roles) {

        this.roles = roles;
        roles.forEach(o -> o.setMember(this));
    }

    public void setAccountBook(AccountBook accountBook){
        this.accountBook = accountBook;
        accountBook.setMember(this);
    }
    public void setPassword(String password){
        this.password=password;
    }
}
