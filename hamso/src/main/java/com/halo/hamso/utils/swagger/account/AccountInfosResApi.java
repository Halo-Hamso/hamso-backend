package com.halo.hamso.utils.swagger.account;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공적으로 데이터를 가져온 경우",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "성공 예제",
                                        value = "{\"pageInfo\": {\"page\": 0, \"pageSize\": 10, \"totalNumber\": 3, \"totalPages\": 1}, " +
                                                "\"accountList\": [" +
                                                "{\"name\": \"김씨\", \"visitedTo\": \"김땡땡\", \"team\": \"함소컴퍼니\", \"relation\": \"직장상사\", \"money\": 10000}," +
                                                "{\"name\": \"이씨\", \"visitedTo\": \"김땡땡\", \"team\": \"함소컴퍼니\", \"relation\": \"친구\", \"money\": 20000}," +
                                                "{\"name\": \"안씨\", \"visitedTo\": \"김땡땡\", \"team\": \"함소컴퍨니\", \"relation\": \"후배\", \"money\": 30000}" +
                                                "]}",
                                        summary = "성공 예제", description = "데이터를 성공적으로 가져온 경우의 예제입니다.")
                        })),
        @ApiResponse(responseCode = "400", description = "요청이 실패한 경우.",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "실패 예제",
                                        value = "회원을 찾을 수 없습니다.",
                                        summary = "실패 예제", description = "회원을 찾을 수 없을 경우의 예외입니다.")
                        }))

})
public @interface AccountInfosResApi {
}
