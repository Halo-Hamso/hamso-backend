package com.halo.hamso.utils.swagger.Auth;


import com.halo.hamso.dto.member.signup.SignUpResDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "요청에 성공하였습니다.",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "성공 예제",
                                        value = "{\"message\": \"요청에 성공하였습니다.\", \"code\": 201, \"response\": {\"name\": \"안희찬\", \"phoneNo\": \"01099267108\"}}",
                                        summary = "성공 예제", description = "요청에 성공한 경우의 예제입니다.")
                        })),
        @ApiResponse(responseCode = "500", description = "요청이 실패하였습니다.",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "실패 예제",
                                        value = "{\"message\": \"01099267108은 이미 존재하는 번호입니다.\", \"code\": 500, \"response\": null}",
                                        summary = "실패 예제", description = "이미 회원가입한 번호일 경우의 예외입니다.")
                        }))
})
public @interface SignUpResApi {
}
