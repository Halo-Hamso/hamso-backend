package com.halo.hamso.repository.account_info;

import com.halo.hamso.dto.account_book.FamilyCntResDto;
import com.halo.hamso.dto.account_book.VisitedToCntResDto;
import com.halo.hamso.dto.chart.HourIntervalMoneyInfo;
import com.halo.hamso.repository.account_book.AccountBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface AccountInfoRepository extends JpaRepository<AccountInfo,Long> {

    @Query(
            "SELECT "
            +"new com.halo.hamso.dto.account_book.VisitedToCntResDto(a.visitedTo, SUM(a.money)) "
            +"FROM AccountInfo a "
            +"GROUP BY a.visitedTo "
    )
    List<VisitedToCntResDto> findVisitedToCntResDtoJPQL();

    @Query(
            "SELECT "
            +"new com.halo.hamso.dto.account_book.FamilyCntResDto(a.familyName, SUM(a.money)) "
            +"FROM AccountInfo a "
            +"GROUP BY a.familyName"
    )
    List<FamilyCntResDto> findFamilyCntResDtoJPQL();

    @Query(
            "SELECT "
            +"new com.halo.hamso.dto.chart.HourIntervalMoneyInfo(function('date_format', ai.createdAt,'%Y-%m-%d %H:00:00'), SUM(ai.money)) "
            +"FROM AccountInfo ai "
            +"WHERE date(ai.createdAt) = :localDate "
            +"GROUP BY function('date_format', ai.createdAt,'%Y-%m-%d %H:00:00') "
            +"ORDER BY function('date_format', ai.createdAt,'%Y-%m-%d %H:00:00') "
    )
    List<HourIntervalMoneyInfo> findByHourJPQL(@Param("localDate") Date localDate);

    Page<AccountInfo> findAllByAccountBook(AccountBook accountBook, Pageable pageable);
    Page<AccountInfo> findAllByTeamOrVisitedToOrRelationOrName(String s1,String s2,String s3, String s4,Pageable pageable);


}
