package com.halo.hamso.repository.account_info;

import com.halo.hamso.dto.account_book.FamilyCntResDto;
import com.halo.hamso.dto.account_book.VisitedToCntResDto;
import com.halo.hamso.repository.account_book.AccountBook;
import com.halo.hamso.repository.account_info.AccountInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AccountInfoRepository extends JpaRepository<AccountInfo,Long> {

    @Query(
            "SELECT "
            +"new com.halo.hamso.dto.account_book.VisitedToCntResDto(a.visitedTo, SUM(a.money)) "
            +"FROM AccountInfo a "
            +"GROUP BY a.visitedTo"
    )
    List<VisitedToCntResDto> findVisitedToCntResDtoJPQL();

    @Query(
            "SELECT "
            +"new com.halo.hamso.dto.account_book.FamilyCntResDto(a.familyName, SUM(a.money)) "
            +"FROM AccountInfo a "
            +"GROUP BY a.familyName"
    )
    List<FamilyCntResDto> findFamilyCntResDtoJPQL();

    Page<AccountInfo> findAllByAccountBook(AccountBook accountBook, Pageable pageable);
    Page<AccountInfo> findAllByTeamOrVisitedToOrRelationOrName(String s1,String s2,String s3, String s4,Pageable pageable);


}
