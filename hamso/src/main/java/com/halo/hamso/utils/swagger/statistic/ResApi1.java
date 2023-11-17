package com.halo.hamso.utils.swagger.statistic;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "데이터 조회 성공",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "성공 예제",
                                        value = "{\"visitedToMoney\": [" +
                                                "{\"visitedTo\": \"임땡땡\", \"money\": 3000}," +
                                                "{\"visitedTo\": \"기땡땡\", \"money\": 2000}," +
                                                "{\"visitedTo\": \"안땡땡\", \"money\": 2250}," +
                                                "{\"visitedTo\": \"지땡땡\", \"money\": 2000}" +
                                                "], " +
                                                "\"familyNameMoney\": [" +
                                                "{\"familyName\": \"임땡땡가족\", \"money\": 5000}," +
                                                "{\"familyName\": \"기땡땡가족\", \"money\": 2000}," +
                                                "{\"familyName\": \"안땡땡가족\", \"money\": 2250}" +
                                                "]}",
                                        summary = "성공 예제", description = "데이터를 성공적으로 조회한 경우의 예제입니다.")
                        })),
        @ApiResponse(responseCode = "400", description = "잘못된 요청 형식입니다.")
})
public @interface ResApi1 {
}
