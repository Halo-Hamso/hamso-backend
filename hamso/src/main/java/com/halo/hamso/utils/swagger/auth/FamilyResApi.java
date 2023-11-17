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
        @ApiResponse(responseCode = "200", description = "가족 정보 조회 성공",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "성공 예제",
                                        value = "[{\"id\": 1, \"familyName\": \"김땡땡가족\"}, " +
                                                "{\"id\": 2, \"familyName\": \"이땡땡가족\"}, " +
                                                "{\"id\": 3, \"familyName\": \"안땡땡가족\"}]",
                                        summary = "성공 예제", description = "가족 정보를 성공적으로 조회한 경우의 예제입니다.")
                        }))
})
public @interface FamilyResApi {
}
