package com.halo.hamso.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByPhoneNo(String phoneNo);
    Optional<Member> findByBusinessId(String businessId);
}
