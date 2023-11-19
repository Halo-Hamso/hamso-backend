package com.halo.hamso.dto.account_book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(name = "견적서 정보")
public class BillInfoReqDto {

    @Schema(example = "식사")
    private String itemType;

    @Schema(example = "230000")
    private Integer cost;

    @Schema(example = "2")
    private Integer count;

    @Schema(example = "2023-11-17T23:00:00")
    private LocalDateTime useTime;
}