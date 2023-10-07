package com.halo.hamso.utils.jwt;

import com.halo.hamso.repository.member.Member;
import com.halo.hamso.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        Member member = memberRepository.findByPhoneNo(phoneNo).orElseThrow(
                () -> new UsernameNotFoundException("전화번호가 존재하지 않습니다.")
        );

        return new CustomUserDetails(member);

    }

}
