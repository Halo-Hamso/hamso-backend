package com.halo.hamso.repository.member;

import com.halo.hamso.repository.account_book.AccountBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByPhoneNo(String phoneNo);
    Optional<Member> findByBusinessId(String businessId);

    Member findByName(String name);

    Member findByAccountBook(AccountBook accountBook);
}
