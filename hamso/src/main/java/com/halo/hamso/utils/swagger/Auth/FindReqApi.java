package com.halo.hamso.utils.swagger.Auth;


import com.halo.hamso.dto.member.find.FindPwdReqDto;
import com.halo.hamso.dto.member.login.LoginReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "비밀번호 변경", description = "요청 받은 정보를 데이터를 이용하여 비밀번호를 변경합니다.")
@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "비밀번호 변경 정보", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = FindPwdReqDto.class)))
public @interface FindReqApi {
}
