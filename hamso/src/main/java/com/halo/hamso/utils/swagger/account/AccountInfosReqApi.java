package com.halo.hamso.utils.swagger.account;


import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "부의금 정보 조회", description = "회원 번호를 통해 해당 회원의 가계부를 요청할 수있습니다."+
        "\n\n 추가적으로 원하는 페이지 번호와 페이지 사이즈를 정해서 보내주시면 됩니다."+
        "\n\n 검색어를 걸어서 요청하면 해당 검색어에 맞는 요소를 반환합니다.")
public @interface AccountInfosReqApi {
}
