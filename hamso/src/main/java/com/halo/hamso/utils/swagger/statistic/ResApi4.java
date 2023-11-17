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
        @ApiResponse(responseCode = "200", description = "조회 성공",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "성공 예제",
                                        value = "{\"profits\": [" +
                                                "{\"date\": \"2023-11-17 08:00:00\", \"money\": 1000}," +
                                                "{\"date\": \"2023-11-17 10:00:00\", \"money\": 1200}," +
                                                "{\"date\": \"2023-11-17 12:00:00\", \"money\": 1900}," +
                                                "{\"date\": \"2023-11-17 16:00:00\", \"money\": 900}," +
                                                "{\"date\": \"2023-11-17 18:00:00\", \"money\": 1300}," +
                                                "{\"date\": \"2023-11-17 20:00:00\", \"money\": 950}," +
                                                "{\"date\": \"2023-11-17 21:00:00\", \"money\": 1200}," +
                                                "{\"date\": \"2023-11-17 23:00:00\", \"money\": 800}" +
                                                "], " +
                                                "\"costs\": [" +
                                                "{\"date\": \"2023-11-17 08:00:00\", \"money\": 12}," +
                                                "{\"date\": \"2023-11-17 09:00:00\", \"money\": 88}," +
                                                "{\"date\": \"2023-11-17 10:00:00\", \"money\": 127}," +
                                                "{\"date\": \"2023-11-17 11:00:00\", \"money\": 51}," +
                                                "{\"date\": \"2023-11-17 12:00:00\", \"money\": 113}," +
                                                "{\"date\": \"2023-11-17 13:00:00\", \"money\": 24}," +
                                                "{\"date\": \"2023-11-17 14:00:00\", \"money\": 49}," +
                                                "{\"date\": \"2023-11-17 15:00:00\", \"money\": 65}," +
                                                "{\"date\": \"2023-11-17 16:00:00\", \"money\": 93}," +
                                                "{\"date\": \"2023-11-17 17:00:00\", \"money\": 11}," +
                                                "{\"date\": \"2023-11-17 18:00:00\", \"money\": 44}," +
                                                "{\"date\": \"2023-11-17 19:00:00\", \"money\": 37}," +
                                                "{\"date\": \"2023-11-17 20:00:00\", \"money\": 32}," +
                                                "{\"date\": \"2023-11-17 21:00:00\", \"money\": 79}," +
                                                "{\"date\": \"2023-11-17 22:00:00\", \"money\": 15}," +
                                                "{\"date\": \"2023-11-17 23:00:00\", \"money\": 19}" +
                                                "]}" +
                                                "}",
                                        summary = "성공 예제", description = "성공적으로 조회한 경우의 예제입니다.")
                        })),
        @ApiResponse(responseCode = "400", description = "잘못된 요청 형식입니다.")
})
public @interface ResApi4 {
}
