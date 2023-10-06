package com.halo.hamso.dto.member.signup;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignUpResDto {

    private String name;
    private String phoneNo;

}
