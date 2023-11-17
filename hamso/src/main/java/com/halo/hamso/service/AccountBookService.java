package com.halo.hamso.service;


import com.halo.hamso.dto.PageInfo;
import com.halo.hamso.dto.account_book.*;
import com.halo.hamso.repository.account_book.AccountBook;
import com.halo.hamso.repository.account_book.AccountBookRepository;
import com.halo.hamso.repository.account_info.AccountInfoRepository;
import com.halo.hamso.repository.account_info.AccountInfo;
import com.halo.hamso.repository.family.Family;
import com.halo.hamso.repository.member.Member;
import com.halo.hamso.repository.member.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountBookService {

    private final AccountInfoRepository accountInfoRepository;
    private final AccountBookRepository accountBookRepository;
    private final MemberRepository memberRepository;


    /**  조문객 조의금 db 저장  */
    @Transactional
    public String registerInfo(Long id, AccountInfoReqDto accountInfoReqDto) throws NotFoundException{
        AccountBook accountBook =accountBookRepository.findById(id)
                .orElseThrow(()->new NotFoundException("회원을 찾을 수 없습니다."));

        AccountInfo accountInfo = AccountInfo.builder()
                .name(accountInfoReqDto.getName())
                .money(accountInfoReqDto.getMoney())
                .team(accountInfoReqDto.getTeam())
                .visitedTo(accountInfoReqDto.getVisitedTo())
                .relation(accountInfoReqDto.getRelation())
                .accountBook(accountBook)
                .build();

        accountInfo.getAccountBook().setTotalProfit(accountInfo.getMoney());
        accountInfo.setFamilyName(memberRepository.findByName(accountInfoReqDto.getVisitedTo()).getFamilyName());

        accountInfoRepository.save(accountInfo);
        return "부의금 데이터 저장 성공";

    }


    /**  가계부 페이징 조회  */
    public AccountInfoPageResDto getAccountBook(Long id, int page, int size, String s) throws NotFoundException
    {
        AccountBook accountBook =accountBookRepository.findById(id)
                .orElseThrow(()->new NotFoundException("회원을 찾을 수 없습니다."));

        Page<AccountInfo> infos;
        // 검색 x
        if(s==null){
            infos = accountInfoRepository.findAllByAccountBook(accountBook,PageRequest.of(page,size));
        }

        // 검색 o
        else{
            infos=accountInfoRepository.findAllByTeamOrVisitedToOrRelationOrName(s,s,s,s,PageRequest.of(page, size));
        }

        PageInfo pageInfo= PageInfo.builder()
                .page(page)
                .pageSize(size)
                .totalPages(infos.getTotalPages())
                .totalNumber(infos.getTotalElements())
                .build();

        List<AccountInfoResDto> results = infos.getContent().stream().map(o -> new AccountInfoResDto(o)
        ).collect(Collectors.toList());

        return AccountInfoPageResDto.builder()
                .pageInfo(pageInfo)
                .accountList(results)
                .build();
    }

    public StatisticsResDto getStatistics(Long id){
        List<VisitedToCntResDto> visitedToMoney = accountInfoRepository.findVisitedToCntResDtoJPQL();
        List<FamilyCntResDto> familyNameMoney = accountInfoRepository.findFamilyCntResDtoJPQL();

        return new StatisticsResDto(visitedToMoney, familyNameMoney);
    }



}
