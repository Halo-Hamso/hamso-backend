package com.halo.hamso.service;


import com.halo.hamso.common.exception.InvalidPasswordException;
import com.halo.hamso.common.exception.MemberDuplicateException;
import com.halo.hamso.dto.member.find.FindPwdReqDto;
import com.halo.hamso.dto.member.login.LoginReqDto;
import com.halo.hamso.dto.member.login.LoginResDto;
import com.halo.hamso.dto.member.signup.SignUpReqDto;
import com.halo.hamso.dto.member.signup.SignUpResDto;
import com.halo.hamso.repository.Authority.Authority;
import com.halo.hamso.repository.account_book.AccountBook;
import com.halo.hamso.repository.account_book.AccountBookRepository;
import com.halo.hamso.repository.family.Family;
import com.halo.hamso.repository.family.FamilyRepository;
import com.halo.hamso.repository.member.Member;
import com.halo.hamso.repository.member.MemberRepository;
import com.halo.hamso.utils.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final FamilyRepository familyRepository;
    private final AccountBookRepository accountBookRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    public List<Family> giveFamilyName(){
        List<Family> familyList = familyRepository.findAll();
        return familyList;
    }
    @Transactional
    public SignUpResDto createMember(SignUpReqDto signUpReqDto) throws MemberDuplicateException{

        // 전화 번호 중복 확인
        Optional<Member> checkMember =memberRepository.findByPhoneNo(signUpReqDto.getPhoneNo());
        if(checkMember.isPresent()) {
            throw new MemberDuplicateException(signUpReqDto.getPhoneNo()+"는 이미 존재하는 번호입니다.");
        }

        if(familyRepository.findByFamilyName(signUpReqDto.getFamilyName())==null){
            familyRepository.save(new Family( signUpReqDto.getName(),signUpReqDto.getFamilyName()));
        }

        Member member=Member.builder()
                .name(signUpReqDto.getName())
                .password(encoder.encode(signUpReqDto.getPassword()))
                .phoneNo(signUpReqDto.getPhoneNo())
                .relation(signUpReqDto.getRelation())
                .build();

        //리스트에 요소 하나만 넣는 것
        if(signUpReqDto.getPhoneNo().equals("01099267107")){
            member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_ADMIN").build()));
        }
        else {
            member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

            //TODO: 장례식 1개만 서비스하는 걸 가정, 즉 회원가입은 오직 한 고인에 대해서만 한다. (이해 안되지만 일단 진행)
            if(accountBookRepository.findAll().isEmpty()){
                member.setAccountBook(Collections.singletonList(AccountBook.builder().totalProfit(0).totalCost(0).totalExpenditure(0).build()));
            }
        }

        member.setFamilyName(signUpReqDto.getFamilyName());
        memberRepository.save(member);

        // Response 생성
        return SignUpResDto.builder()
                .name(member.getName())
                .phoneNo(member.getPhoneNo())
                .build();
    }

    // Login 과정
    @Transactional
    public LoginResDto login(LoginReqDto memberInfo) throws NotFoundException,InvalidPasswordException {

        // userName X
        Member member = memberRepository.findByPhoneNo(memberInfo.getPhoneNo())
                .orElseThrow(() -> new NotFoundException(memberInfo.getPhoneNo()+"에 해당하는 회원 정보가 존재하지 않습니다.")); // 404
        // password wrong
        if(!encoder.matches(memberInfo.getPassword(), member.getPassword())) {
            throw new InvalidPasswordException("잘못된 비밀번호 입니다."); // 401
        }

        return LoginResDto.builder()
                .memberId(member.getId())
                .name(member.getName())
                .phoneNo(memberInfo.getPhoneNo())
                .token(jwtProvider.createToken(member.getPhoneNo(),member.getRoles()))
                .role(member.getRoles().get(0).getName())
                .build();
    }

    @Transactional
    public String findAndUpdatePassword(FindPwdReqDto pwdReqDto) throws NotFoundException {
        Optional<Member> member = memberRepository.findByPhoneNo(pwdReqDto.getPhoneNo());
        if(member.isEmpty()) {
            throw new NotFoundException("존재하지 않는 회원의 핸드폰 번호 정보입니다."); //404
        }
        Member tmp = member.get();

        tmp.setPassword(encoder.encode(pwdReqDto.getNewPassword()));
        memberRepository.save(tmp);

        return "비밀번호 변경에 성공하였습니다.";
    }

}
