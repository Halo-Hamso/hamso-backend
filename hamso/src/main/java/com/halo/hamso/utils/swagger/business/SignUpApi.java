package com.halo.hamso.utils.swagger.business;


import com.halo.hamso.dto.business.BusinessSignUpReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "비즈니스 회원가입", description = "요청 받은 회원정보 데이터를 이용하여 비즈니스 회원가입을 진행합니다.")
@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "비즈니스 회원 정보", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = BusinessSignUpReqDto.class)))
public @interface SignUpApi {
}
