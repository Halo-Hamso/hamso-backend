package com.halo.hamso.utils.swagger.sms;


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
                                        value = "128034",
                                        summary = "성공 예제", description = "요청이 성공한 경우의 예제입니다.")
                        })),
        @ApiResponse(responseCode = "500", description = "요청이 실패한 경우",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "실패 예제",
                                        value = "아직 각각의 예외에 대해 모두 처리하지 않아서 오류가 발생하면 말해주세요. 대부분 번호를" +
                                                "잘못 입력 하였을 경우에 발생할 수 있습니다.",
                                        summary = "실패 예제", description = "실패시 예제입니다.")
                        })),

})
public @interface SmsSendResApi {
}
