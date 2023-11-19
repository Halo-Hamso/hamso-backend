package com.halo.hamso.service;


import com.halo.hamso.common.exception.MemberDuplicateException;
import com.halo.hamso.common.exception.TimeDuplicateException;
import com.halo.hamso.dto.PageInfo;
import com.halo.hamso.dto.account_book.*;
import com.halo.hamso.dto.chart.AllHourIntervalResDto;
import com.halo.hamso.dto.chart.HourIntervalMoneyInfo;
import com.halo.hamso.repository.account_book.AccountBook;
import com.halo.hamso.repository.account_book.AccountBookRepository;
import com.halo.hamso.repository.account_info.AccountInfoRepository;
import com.halo.hamso.repository.account_info.AccountInfo;
import com.halo.hamso.repository.bill_info.BillInfo;
import com.halo.hamso.repository.bill_info.BillInfoRepository;
import com.halo.hamso.repository.family.FamilyRepository;
import com.halo.hamso.repository.member.Member;
import com.halo.hamso.repository.member.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AccountBookService {

    private final AccountInfoRepository accountInfoRepository;
    private final AccountBookRepository accountBookRepository;
    private final MemberRepository memberRepository;
    private final BillInfoRepository billInfoRepository;
    private final FamilyRepository familyRepository;



    /**  조문객 조의금 db 저장  */
    @Transactional
    public String registerInfo(Long id, AccountInfoReqDto accountInfoReqDto) throws NotFoundException{
        AccountBook accountBook =accountBookRepository.findById(id)
                .orElseThrow(()->new NotFoundException("가계부를 찾을 수 없습니다."));

        AccountInfo accountInfo = AccountInfo.builder()
                .name(accountInfoReqDto.getName())
                .money(accountInfoReqDto.getMoney())
                .team(accountInfoReqDto.getTeam())
                .visitedTo(accountInfoReqDto.getVisitedTo())
                .relation(accountInfoReqDto.getRelation())
                .accountBook(accountBook)
                .build();

        accountInfo.getAccountBook().setTotalProfit(accountInfo.getMoney());
        accountInfo.setFamilyName(
                familyRepository.findByName(accountInfoReqDto.getVisitedTo()).orElseThrow(()->new NotFoundException("방문 유족이 가족에 등록되어 있지 않습니다. 가족에 등록 하고 다시 시도해 주세요"))
                        .getFamilyName()
        );

        accountInfoRepository.save(accountInfo);
        return "부의금 데이터 저장 성공";

    }


    /**  가계부 페이징 조회  */
    public AccountInfoPageResDto getAccountBook(Long id, int page, int size, String s) throws NotFoundException
    {
        AccountBook accountBook =accountBookRepository.findById(id)
                .orElseThrow(()->new NotFoundException("가계부를 찾을 수 없습니다."));

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

    public BillInfoPageResDto getBillByItem(int page, int size){
        Page<BillInfoByItemType> billPageInfos = billInfoRepository.findAllByItemJPQL(PageRequest.of(page,size));

        PageInfo pageInfo= PageInfo.builder()
                .page(page)
                .pageSize(size)
                .totalPages(billPageInfos.getTotalPages())
                .totalNumber(billPageInfos.getTotalElements())
                .build();

        List<BillInfoByItemType> billInfos = billPageInfos.getContent();

        return BillInfoPageResDto.builder()
                .billInfos(billInfos)
                .pageInfo(pageInfo)
                .build();
    }

    public BillInfoPageResDto getBillByUseTime(int page, int size){
        Page<BillInfo> billPageInfos = billInfoRepository.findAll(PageRequest.of(page,size,Sort.by(Sort.Direction.DESC,"useTime")));

        PageInfo pageInfo= PageInfo.builder()
                .page(page)
                .pageSize(size)
                .totalPages(billPageInfos.getTotalPages())
                .totalNumber(billPageInfos.getTotalElements())
                .build();

        List<BillInfoByUseTime> billInfos = billPageInfos.getContent()
                .stream().map(o->new BillInfoByUseTime(o)).collect(Collectors.toList());

        return BillInfoPageResDto.builder()
                .billInfos(billInfos)
                .pageInfo(pageInfo)
                .build();
    }

    public AllHourIntervalResDto getAccountByDateTime(Date date, Integer option){

        log.info("date: {}", date);

        if(option == 0){ // 전체 조회

            List<HourIntervalMoneyInfo> profits = accountInfoRepository.findByHourJPQL(date);
            for(HourIntervalMoneyInfo elem : profits){
                elem.setDate(elem.getDate().substring(11,16));
            }
            List<HourIntervalMoneyInfo> costs = billInfoRepository.findByHourJPQL(date);
            for(HourIntervalMoneyInfo elem : costs){
                elem.setDate(elem.getDate().substring(11,16));
            }

            return AllHourIntervalResDto.builder().profits(profits).costs(costs).build();
        }
        else if(option == 1){
            List<HourIntervalMoneyInfo> profits = accountInfoRepository.findByHourJPQL(date);
            for(HourIntervalMoneyInfo elem : profits){
                elem.setDate(elem.getDate().substring(11,16));
            }

            return AllHourIntervalResDto.builder().profits(profits).costs(null).build();
        }
        else {
            List<HourIntervalMoneyInfo> costs = billInfoRepository.findByHourJPQL(date);
            for(HourIntervalMoneyInfo elem : costs){
                elem.setDate(elem.getDate().substring(11,16));
            }

            return AllHourIntervalResDto.builder().profits(null).costs(costs).build();
        }

    }

    @Transactional
    public BillInfoResDto billRegisterInfo(Long id, BillInfoReqDto billInfoReqDto) throws  NotFoundException {

        // 시간 중복 확인
//        Optional<BillInfo> checkBillTime = accountBookRepository.findByUseTime(billInfoReqDto.getUseTime());
//        if (checkBillTime.isPresent()) {
//            throw new TimeDuplicateException(billInfoReqDto.getUseTime() + "는 이미 존재하는 시간입니다.");
//        }

        AccountBook accountBook = accountBookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("가계부를 찾을 수 없습니다."));

        BillInfo billInfo = BillInfo.builder()
                .itemType(billInfoReqDto.getItemType())
                .cost(billInfoReqDto.getCost())
                .count(billInfoReqDto.getCount())
                .useTime(billInfoReqDto.getUseTime())
                .accountBook(accountBook)
                .build();

        billInfo.getAccountBook().setTotalExpenditure(billInfo.getCost() * billInfo.getCount());
        billInfoRepository.save(billInfo);

        // Response 생셩
        return BillInfoResDto.builder()
                .itemType(billInfo.getItemType())
                .cost(billInfo.getCost())
                .count(billInfo.getCount())
                .useTime(billInfo.getUseTime())
                .build();
    }

}
