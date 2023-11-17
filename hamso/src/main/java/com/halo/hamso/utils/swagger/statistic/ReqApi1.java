package com.halo.hamso.utils.swagger.statistic;


import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "가족별, 방문 유족별 통계", description = "id에 1을 넣어주세요. 장례식 하나만을 가정하므로 계좌는 오직 하나")
public @interface ReqApi1 {
}
