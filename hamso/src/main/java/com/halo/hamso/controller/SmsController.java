package com.halo.hamso.controller;


import com.halo.hamso.dto.sms.MessageDto;
import com.halo.hamso.service.SmsService;
import com.halo.hamso.utils.swagger.sms.SmsSendReqApi;
import com.halo.hamso.utils.swagger.sms.SmsSendResApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/sms")
@Tag(name = "문자 인증 API", description = "번호로 문자 인증 API")
@Slf4j
public class SmsController {

    private final SmsService smsService;
    /** 해당하는 번호로 인증번호 발송하기 */

    @SmsSendReqApi
    @SmsSendResApi
    @PostMapping("/send")
    public ResponseEntity<?> sendSMS(@RequestBody MessageDto messageDto){
        try{
            log.info("sms 문자 보내기 시작");
            String code = smsService.sendSms(messageDto);
            log.info("sms 문자 보내기 종료");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(smsService.sendSms(messageDto));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }
}
