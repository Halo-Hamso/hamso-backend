package com.halo.hamso.utils.swagger.sms;


import com.halo.hamso.dto.member.signup.SignUpReqDto;
import com.halo.hamso.dto.sms.MessageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "문자 인증", description = "요청 문자 인증 데이터를 이용하여 회원가입을 진행합니다. 문자 인증 데이터의 경우 인증할 번호만 보내주면 됩니다. content는 비워주세요 ")
@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "메시지 정보", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = MessageDto.class)))
public @interface SmsSendReqApi {
}
