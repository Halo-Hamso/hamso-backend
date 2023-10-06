package com.halo.hamso.repository.member;


import com.halo.hamso.dto.member.signup.SignUpReqDto;
import com.halo.hamso.repository.family.FamilyGroup;
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
    private List<FamilyGroup> familyGroups= new ArrayList<FamilyGroup>();

    @OneToMany(mappedBy = "member")
    private List<Funeral> funeralList = new ArrayList<Funeral>();

    @Builder
    public Member(SignUpReqDto signUpReqDto) {
        this.name = signUpReqDto.getName();
        this.phoneNo = signUpReqDto.getPhoneNo();
        this.password = signUpReqDto.getPassword();
        this.relation = signUpReqDto.getRelation();
    }
}
