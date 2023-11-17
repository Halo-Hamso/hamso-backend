package com.halo.hamso.controller;


import com.halo.hamso.common.exception.InvalidPasswordException;
import com.halo.hamso.common.exception.MemberDuplicateException;
import com.halo.hamso.dto.business.BusinessLoginDto;
import com.halo.hamso.dto.business.BusinessSignUpReqDto;
import com.halo.hamso.dto.member.login.LoginReqDto;
import com.halo.hamso.dto.member.login.LoginResDto;
import com.halo.hamso.dto.member.signup.SignUpReqDto;
import com.halo.hamso.dto.member.signup.SignUpResDto;
import com.halo.hamso.service.BusinessService;
import com.halo.hamso.utils.swagger.auth.LoginReqApi;
import com.halo.hamso.utils.swagger.auth.LoginResApi;
import com.halo.hamso.utils.swagger.auth.SignUpReqApi;
import com.halo.hamso.utils.swagger.auth.SignUpResApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;


@Tag(name = "사업자(business) API", description = "사업자 회원가입, 지출 내역 등록")
@RestController
@RequiredArgsConstructor
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;


    @PostMapping("/signup")
    public ResponseEntity<?> createMember(@RequestBody BusinessSignUpReqDto businessSignUpReqDto){

        try{
            SignUpResDto signUpResDto = businessService.createBusiness(businessSignUpReqDto);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(signUpResDto);
        }

        catch(MemberDuplicateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    /** 로그인 */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody BusinessLoginDto businessLoginDto) {
        try {
            LoginResDto loginResDto = businessService.login(businessLoginDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(loginResDto);
        }
        catch(NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        catch(InvalidPasswordException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


}
