package com.halo.hamso.dto.business;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "비즈니스 로그인 정보")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessLoginDto {

    @Schema(example = "ahc700")
    private String businessId;

    @Schema(example = "1234")
    private String password;
}
