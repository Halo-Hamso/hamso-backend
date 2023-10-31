package com.halo.hamso.dto.account_book;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(name = "부의금 정보")
public class AccountInfoReqDto {

    @Schema(example = "1")
    private Long memberId;

    @Schema(example = "김땡땡")
    private String name;

    @Schema(example = "함소컴퍼니")
    private String team;

    @Schema(example = "이땡땡")
    private String visitedTo;

    @Schema(example = "회사상사")
    private String relation;

    @Schema(example = "100000")
    private Integer money;
}
