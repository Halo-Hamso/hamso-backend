package com.halo.hamso.dto.sms;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="문자 인증 정보")
public class MessageDto {

    @Schema(example = "01099267107")
    private String to;

    @Schema(example = "null")
    private String content;
}
