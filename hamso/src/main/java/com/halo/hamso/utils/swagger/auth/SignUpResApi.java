package com.halo.hamso.utils.swagger.auth;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공적으로 처리된 경우",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "성공 예제",
                                        value = "{\"name\": \"안희찬\", \"phoneNo\": \"01099267107\"}",
                                        summary = "성공 예제", description = "요청이 성공한 경우의 예제입니다.")
                        })),
        @ApiResponse(responseCode = "500", description = "요청이 실패하였습니다.",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "실패 예제",
                                        value = "01099267108은 이미 존재하는 번호입니다.",
                                        summary = "실패 예제", description = "이미 회원가입한 번호일 경우의 예외입니다.")
                        }))
})
public @interface SignUpResApi {
}
