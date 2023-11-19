package com.halo.hamso.controller;


import com.halo.hamso.common.exception.InvalidPasswordException;
import com.halo.hamso.common.exception.MemberDuplicateException;
import com.halo.hamso.dto.account_book.BillInfoReqDto;
import com.halo.hamso.dto.business.BusinessLoginDto;
import com.halo.hamso.dto.business.BusinessSignUpReqDto;
import com.halo.hamso.dto.business.FindDupReqDto;
import com.halo.hamso.dto.member.login.LoginResDto;
import com.halo.hamso.dto.member.signup.SignUpResDto;
import com.halo.hamso.service.BusinessService;
import com.halo.hamso.utils.swagger.auth.SignUpResApi;
import com.halo.hamso.utils.swagger.business.DupApi;
import com.halo.hamso.utils.swagger.business.LoginApi;
import com.halo.hamso.utils.swagger.business.SignUpApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;


@Tag(name = "사업자(business) API", description = "사업자 회원가입, 지출 내역 등록")
@RestController
@RequiredArgsConstructor
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;


    @PostMapping("/signup")
    @SignUpApi
    @SignUpResApi
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

    @LoginApi
    @SignUpResApi
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

    @DupApi
    @PostMapping("/id")
    public Boolean findDuplicate(@RequestBody FindDupReqDto findDupReqDto){
        return businessService.findDuplicate(findDupReqDto);
    }
}
