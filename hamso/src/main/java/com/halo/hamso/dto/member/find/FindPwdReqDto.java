package com.halo.hamso.dto.member.find;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "비밀번호 변경 정보")
public class FindPwdReqDto {

    @Schema(example = "01099267107")
    private String phoneNo;

    @Schema(example = "123")
    private String newPassword;
}
