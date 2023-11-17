package com.halo.hamso.utils.swagger.statistic;


import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "실시간 통계", description = "page 번호와 size를 param 형식으로 제공해 주세요")
public @interface ReqApi3 {
}
