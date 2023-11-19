package com.halo.hamso.service;


import com.halo.hamso.common.exception.InvalidPasswordException;
import com.halo.hamso.common.exception.MemberDuplicateException;
import com.halo.hamso.dto.account_book.BillInfoReqDto;
import com.halo.hamso.dto.business.BusinessLoginDto;
import com.halo.hamso.dto.business.BusinessSignUpReqDto;
import com.halo.hamso.dto.business.FindDupReqDto;
import com.halo.hamso.dto.member.login.LoginResDto;
import com.halo.hamso.dto.member.signup.SignUpResDto;
import com.halo.hamso.repository.Authority.Authority;
import com.halo.hamso.repository.account_book.AccountBook;
import com.halo.hamso.repository.account_book.AccountBookRepository;
import com.halo.hamso.repository.bill_info.BillInfo;
import com.halo.hamso.repository.bill_info.BillInfoRepository;
import com.halo.hamso.repository.member.Member;
import com.halo.hamso.repository.member.MemberRepository;
import com.halo.hamso.utils.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final MemberRepository memberRepository;
    private final AccountBookRepository accountBookRepository;
    private final BillInfoRepository billInfoRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public SignUpResDto createBusiness(BusinessSignUpReqDto businessSignUpReqDto) throws MemberDuplicateException {

        // 전화 번호 중복 확인
        Optional<Member> checkMember =memberRepository.findByPhoneNo(businessSignUpReqDto.getPhoneNo());
        if(checkMember.isPresent()) {
            throw new MemberDuplicateException(businessSignUpReqDto.getPhoneNo()+"는 이미 존재하는 번호입니다.");
        }

        Member member=new Member(businessSignUpReqDto);
        member.setPassword(encoder.encode(businessSignUpReqDto.getPassword()));
        member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_BUSINESS").build()));
        memberRepository.save(member);

        // Response 생성
        return SignUpResDto.builder()
                .name(member.getName())
                .phoneNo(member.getPhoneNo())
                .build();
    }

    @Transactional
    public LoginResDto login(BusinessLoginDto memberInfo) throws NotFoundException, InvalidPasswordException {

        // userName X
        Member member = memberRepository.findByBusinessId(memberInfo.getBusinessId())
                .orElseThrow(() -> new NotFoundException(memberInfo.getBusinessId()+"에 해당하는 회원 정보가 존재하지 않습니다.")); // 404

        if(!encoder.matches(memberInfo.getPassword(), member.getPassword())) {
            throw new InvalidPasswordException("잘못된 비밀번호 입니다."); // 401
        }

        return LoginResDto.builder()
                .memberId(member.getId())
                .phoneNo(member.getPhoneNo())
                .name(member.getName())
                .token(jwtProvider.createToken(member.getPhoneNo(),member.getRoles()))
                .businessId(member.getBusinessId())
                .role(member.getRoles().get(0).getName())
                .build();
    }

    public Boolean findDuplicate(FindDupReqDto findDupReqDto){
        if(memberRepository.findByBusinessId(findDupReqDto.getBusinessId()).isPresent()){
            return false;
        }
        else{
            return true;
        }
    }

    @Transactional
    public String billRegisterInfo(Long id, BillInfoReqDto billInfoReqDto) throws  NotFoundException {
        AccountBook accountBook =accountBookRepository.findById(id)
                .orElseThrow(()->new NotFoundException("회원을 찾을 수 없습니다."));

        BillInfo billInfo = BillInfo.builder()
                .itemType(billInfoReqDto.getItemType())
                .cost(billInfoReqDto.getCost())
                .count(billInfoReqDto.getCount())
                .useTime(billInfoReqDto.getUseTime())
                .accountBook(accountBook)
                .build();

        billInfo.getAccountBook().setTotalExpenditure(billInfo.getCost() * billInfo.getCount());

        billInfoRepository.save(billInfo);
        return "견적서 데이터 저장 성공";
    }
}
