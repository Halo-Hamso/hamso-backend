package com.halo.hamso.dto.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SmsResDto {
    String requestId;
    LocalDateTime requestTime;
    String statusCode;
    String statusName;
}
