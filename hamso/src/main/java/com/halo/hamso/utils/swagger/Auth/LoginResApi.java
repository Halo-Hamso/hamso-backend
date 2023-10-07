package com.halo.hamso.utils.swagger.Auth;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "요청이 성공한 경우",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "성공 예제",
                                        value = "{\"phoneNo\": \"01099267107\", \"name\": \"안희찬\", \"token\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMTA5OTI2NzEwNyIsInJvbGVzIjpbXSwiaWF0IjoxNjk2NjY1NjE1LCJleHAiOjE2OTY2NjU3MDF9.0_fQo-uI_fn560Sp2rfyCs1Edn24FjTG_uOAUG6abVs\"}",
                                        summary = "성공 예제", description = "요청이 성공한 경우의 예제입니다.")
                        })),
        @ApiResponse(responseCode = "404", description = "요청이 실패한 경우.",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "실패 예제",
                                        value = "잘못된 비밀번호 입니다.",
                                        summary = "실패 예제", description = "잘못된 비밀번호일 경우의 예외입니다.")
                        })),
        @ApiResponse(responseCode = "400", description = "요청이 실패한 경우",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "실패 예제",
                                        value = "0109926717에 해당하는 회원 정보가 존재하지 않습니다.",
                                        summary = "실패 예제", description = "회원이 아닐 경우의 예외입니다.")
                        })),

})
public @interface LoginResApi {
}
