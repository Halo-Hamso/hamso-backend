package com.halo.hamso.repository.account_book;

import com.halo.hamso.repository.bill_info.BillInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AccountBookRepository extends JpaRepository<AccountBook,Long> {

    //Optional<BillInfo> findByUseTime(LocalDateTime useTime);
}
