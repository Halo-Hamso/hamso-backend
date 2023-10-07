package com.halo.hamso.utils.swagger.Auth;


import com.halo.hamso.dto.member.login.LoginReqDto;
import com.halo.hamso.dto.member.signup.SignUpReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "로그인", description = "요청 받은 로그인 정보 데이터를 이용하여 로그인을 진행합니다.")
@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "로그인 정보", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = LoginReqDto.class)))
public @interface LoginReqApi {
}
