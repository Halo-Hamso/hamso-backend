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
                                                "{\"itemType\": \"식사\", \"count\": 16, \"cost\": 329}," +
                                                "{\"itemType\": \"관\", \"count\": 10, \"cost\": 143}," +
                                                "{\"itemType\": \"용품\", \"count\": 8, \"cost\": 195}," +
                                                "{\"itemType\": \"정장\", \"count\": 10, \"cost\": 192}" +
                                                "], " +
                                                "\"pageInfo\": {" +
                                                "\"page\": 0, \"pageSize\": 10, \"totalNumber\": 4, \"totalPages\": 1" +
                                                "}}",
                                        summary = "성공 예제", description = "지출 정보를 성공적으로 조회한 경우의 예제입니다.")
                        }))
})
public @interface ResApi2 {
}
