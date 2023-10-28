package com.halo.hamso.repository.account_book;

import com.halo.hamso.repository.account_info.AccountInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<AccountInfo,Long> {

    Page<AccountInfo> findAllByAccountBook(AccountBook accountBook, Pageable pageable);
    Page<AccountInfo> findAllByTeamOrVisitedToOrRelationOrName(String s1,String s2,String s3, String s4,Pageable pageable);


}
