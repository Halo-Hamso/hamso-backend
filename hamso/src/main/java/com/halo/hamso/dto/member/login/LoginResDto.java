package com.halo.hamso.dto.member.login;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResDto {
    private Long memberId;
    private String phoneNo;
    private String name;
    private String token;
    private String role;
    private String businessId;
}
