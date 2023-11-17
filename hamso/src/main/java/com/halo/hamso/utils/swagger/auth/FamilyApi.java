package com.halo.hamso.utils.swagger.auth;



import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "가족 정보 제공", description = "그대로 조회하면 현재 등록된 가족 정보를 제공합니다. 아무것도 없을 시 빈 리스트 제공")
public @interface FamilyApi {
}
