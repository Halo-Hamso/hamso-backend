package com.halo.hamso.repository.family;


import com.halo.hamso.repository.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class FamilyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String familyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

}
