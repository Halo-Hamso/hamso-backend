package com.halo.hamso.service;


import com.halo.hamso.common.exception.MemberDuplicateException;
import com.halo.hamso.dto.member.signup.SignUpReqDto;
import com.halo.hamso.dto.member.signup.SignUpResDto;
import com.halo.hamso.repository.member.Member;
import com.halo.hamso.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    @Transactional
    public SignUpResDto createMember(SignUpReqDto signUpReqDto) throws MemberDuplicateException{



        // 전화 번호 중복 확인
        Optional<Member> checkMember =memberRepository.findByPhoneNo(signUpReqDto.getPhoneNo());
        if(checkMember.isPresent()) {
            throw new MemberDuplicateException(signUpReqDto.getPhoneNo()+"는 이미 존재하는 번호입니다.");
        }

        Member member=Member.builder()
                .signUpReqDto(signUpReqDto)
                .build();
        memberRepository.save(member);

        // Response 생성
        return SignUpResDto.builder()
                .name(member.getName())
                .phoneNo(member.getPhoneNo())
                .build();
    }

}
