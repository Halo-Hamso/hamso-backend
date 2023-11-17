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
        @ApiResponse(responseCode = "200", description = "지출 정보 조회 성공",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "성공 예제",
                                        value = "{\"billInfos\": [" +
                                                "{\"itemType\": \"식사\", \"count\": 2, \"cost\": 19, \"useTime\": \"2023-11-17T23:00:00\"}," +
                                                "{\"itemType\": \"식사\", \"count\": 1, \"cost\": 15, \"useTime\": \"2023-11-17T22:00:00\"}," +
                                                "{\"itemType\": \"정장\", \"count\": 3, \"cost\": 79, \"useTime\": \"2023-11-17T21:00:00\"}," +
                                                "{\"itemType\": \"용품\", \"count\": 2, \"cost\": 32, \"useTime\": \"2023-11-17T20:00:00\"}," +
                                                "{\"itemType\": \"정장\", \"count\": 1, \"cost\": 37, \"useTime\": \"2023-11-17T19:15:00\"}," +
                                                "{\"itemType\": \"관\", \"count\": 4, \"cost\": 44, \"useTime\": \"2023-11-17T18:15:00\"}," +
                                                "{\"itemType\": \"정장\", \"count\": 2, \"cost\": 11, \"useTime\": \"2023-11-17T17:30:00\"}," +
                                                "{\"itemType\": \"식사\", \"count\": 1, \"cost\": 79, \"useTime\": \"2023-11-17T16:30:00\"}," +
                                                "{\"itemType\": \"관\", \"count\": 3, \"cost\": 14, \"useTime\": \"2023-11-17T16:00:00\"}," +
                                                "{\"itemType\": \"정장\", \"count\": 4, \"cost\": 65, \"useTime\": \"2023-11-17T15:45:00\"}" +
                                                "], " +
                                                "\"pageInfo\": {" +
                                                "\"page\": 0, \"pageSize\": 10, \"totalNumber\": 20, \"totalPages\": 2" +
                                                "}}",
                                        summary = "성공 예제", description = "지출 정보를 성공적으로 조회한 경우의 예제입니다.")
                        })),
})
public @interface ResApi3 {
}
