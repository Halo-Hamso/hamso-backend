package com.halo.hamso.utils.swagger.statistic;


import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "날짜별 수익, 지출 그래프", description = "date에는 원하는 날짜를 yyyy-mm-dd 형식으로 제공해주세요"+
        "\n\n 추가적으로 option에는 숫자 0,1,2 를 전달해 주세요"+
        "\n\n 0은 전체, 1은 수익, 2는 지출 Response 예시는 0일때 입니다. 1과 2일때는 해당하지 않는 리스트는 null로 반환됩니다.")
public @interface ReqApi4 {
}
