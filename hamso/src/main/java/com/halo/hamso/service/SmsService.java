package com.halo.hamso.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.halo.hamso.dto.sms.MessageDto;
import com.halo.hamso.dto.sms.SmsReqDto;
import com.halo.hamso.dto.sms.SmsResDto;
import com.halo.hamso.utils.sms.MakeSmsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class SmsService {

    private final MakeSmsRequest makeSmsRequest;
    /** 인증코드 생성 및 네이버 클라우드에 요청 보내기 */
    public String sendSms(MessageDto messageDto) throws Exception{

        // 1. 네이버 클라우드 API에게 보낼 메시지와 Code 생성
        Random rand = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            code += ran;
        }

        messageDto.setContent("[" + code + "]" + "<함소>인증번호를 3분 내에 입력해주세요.");

        // 2. 네이버 클라우드 API에게 Request 보내기

        // 헤더 --> TODO: header의 내용중 암호화해야 하는 것이 존재해서 해당 내용 예외처리 해야함
        HttpHeaders header = makeSmsRequest.getSmsHeader();
        // Body
        SmsReqDto smsReqDto = makeSmsRequest.getSmsBody(messageDto);
        //요청 보내기 --> TODO: Json을 String으로 바꿀때 예외처리랑, URI 예외처리 해야함
        SmsResDto smsResDto=makeSmsRequest.sendSmsReq(header, smsReqDto);

        return code;
    }


}
