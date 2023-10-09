package com.halo.hamso.dto.sms;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String to;
    private String content;
}
