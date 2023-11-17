package com.halo.hamso.dto.member.signup;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "회원정보")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpReqDto {

    @Schema(example = "안희찬")
    private String name;

    @Schema(example ="01099267107")
    private String phoneNo;

    @Schema(example = "1234")
    private String password;

    @Schema(example = "기타")
    private String relation;

    @Schema(example = "김땡땡 가족")
    private String familyName;

}
