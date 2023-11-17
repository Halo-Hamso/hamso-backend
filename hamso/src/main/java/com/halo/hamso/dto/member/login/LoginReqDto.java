package com.halo.hamso.dto.member.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(name = "유저 로그인 정보")
public class LoginReqDto {

    @Schema(example = "01099267108")
    private String phoneNo;

    @Schema(example = "1234")
    private String password;
}
