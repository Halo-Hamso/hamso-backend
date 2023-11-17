package com.halo.hamso.dto.business;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "비즈니스 회원 정보")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessSignUpReqDto {

    @Schema(example = "안희찬")
    private String name;

    @Schema(example = "ahc700")
    private String businessId;

    @Schema(example = "1234")
    private String password;

    @Schema(example = "01099267107")
    private String phoneNo;

    @Schema(example = "헤일로")
    private String businessName;

    @Schema(example = "427-34-01442")
    private String businessNo;

    @Schema(example = "사업시설관리")
    private String businessType;

    @Schema(example = "서비스업")
    private String businessItem;
}
