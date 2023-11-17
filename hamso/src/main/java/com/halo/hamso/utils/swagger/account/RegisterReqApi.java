package com.halo.hamso.utils.swagger.account;


import com.halo.hamso.dto.account_book.AccountInfoReqDto;
import com.halo.hamso.dto.member.find.FindPwdReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "부의금 정보 등록", description = "id값은 1로 전달해주세요 --> 테스트 단계 가계부 하나로 고정")
@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "부의금 정보", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = AccountInfoReqDto.class)))
public @interface RegisterReqApi {
}
