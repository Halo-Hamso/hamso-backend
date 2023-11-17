package com.halo.hamso.utils.swagger.business;

import com.halo.hamso.dto.business.FindDupReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "아이디 중복 확인", description = "businessId가 중복 되는지 확인, 중복되면 false 중복이 아니라면 true 반환")
@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "중복 확인", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = FindDupReqDto.class)))
public @interface DupApi {
}
