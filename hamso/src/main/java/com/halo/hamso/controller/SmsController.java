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
@Slf4j
@Tag(name = "문자 인증 API", description = "번호로 문자 인증 API")
public class SmsController {

    private final SmsService smsService;
//    private final HttpSession session;
    /** 해당하는 번호로 인증번호 발송하기 */

    @SmsSendReqApi
    @SmsSendResApi
    @PostMapping("/send")
    public ResponseEntity<?> sendSMS(@RequestBody MessageDto messageDto){
        try{
            String code = smsService.sendSms(messageDto);
//            session.setAttribute("rand", code);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(smsService.sendSms(messageDto));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }


    /** 인증번호 검증  test 용 */
//    @GetMapping("/check")
//    public ResponseEntity<Boolean> phoneAuthOk(@RequestParam String code) {
//        String rand = (String) session.getAttribute("rand");
//
//        log.info("rand = {}, code = {}", rand, code);
//
//        if (rand.equals(code)) {
//            session.removeAttribute("rand");
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(false, HttpStatus.OK);
//    }
}
