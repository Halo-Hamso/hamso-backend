package com.halo.hamso.repository.bill_info;

import com.halo.hamso.dto.account_book.BillInfoByItemType;
import com.halo.hamso.dto.account_book.BillInfoByUseTime;
import com.halo.hamso.repository.account_book.AccountBook;
import com.halo.hamso.repository.account_info.AccountInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillInfoRepository extends JpaRepository<BillInfo,Long> {

    @Query(
            "SELECT "
            +"new com.halo.hamso.dto.account_book.BillInfoByItemType(b.itemType, SUM(b.count), SUM(b.cost)) "
            +"FROM BillInfo b "
            +"GROUP BY b.itemType"
    )
    Page<BillInfoByItemType> findAllByItemJPQL(Pageable pageable);

    Page<BillInfo> findAll(Pageable pageable);
}
