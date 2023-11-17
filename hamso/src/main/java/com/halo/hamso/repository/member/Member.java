package com.halo.hamso.repository.member;



import com.halo.hamso.dto.business.BusinessSignUpReqDto;
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

    @Column(nullable = true)
    private String businessId;

    @Column(nullable = false)
    private String phoneNo;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String relation;

    @Column(nullable = true)
    private String businessName;

    @Column(nullable = true)
    private String businessNo;

    @Column(nullable = true)
    private String businessType;

    @Column(nullable = true)
    private String businessCategory;




    @OneToMany(mappedBy = "member")
    private List<Funeral> funeralList = new ArrayList<Funeral>();

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<AccountBook> accountBook = new ArrayList<>();


    /** 일반 회원 가입 */
    @Builder
    public Member(String name, String phoneNo, String password, String relation) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.password = password;
        this.relation = relation;
    }


    /** 사업자 회원가입 */
    public Member(BusinessSignUpReqDto dto) {
        this.name = dto.getName();
        this.businessId = dto.getBusinessId();
        this.phoneNo = dto.getPhoneNo();
        this.businessName = dto.getBusinessName();
        this.businessNo = dto.getBusinessNo();
        this.businessType = dto.getBusinessType();
        this.businessCategory = dto.getBusinessCategory();
    }

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Authority> roles = new ArrayList<>();


    public void setRoles(List<Authority> roles) {

        this.roles = roles;
        roles.forEach(o -> o.setMember(this));
    }

    public void setAccountBook(List<AccountBook> accountBook){
        this.accountBook = accountBook;
        accountBook.forEach(o->o.setMember(this));
    }
    public void setPassword(String password){
        this.password=password;
    }
}
