package com.halo.hamso.controller;


import com.halo.hamso.common.BaseResponse;
import com.halo.hamso.common.exception.MemberDuplicateException;
import com.halo.hamso.dto.member.signup.SignUpReqDto;
import com.halo.hamso.dto.member.signup.SignUpResDto;
import com.halo.hamso.service.AuthService;
import com.halo.hamso.utils.swagger.Auth.SignUpReqApi;
import com.halo.hamso.utils.swagger.Auth.SignUpResApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원가입", description = "회원가입, 로그인, 비밀번호 찾기, 아이디 중복 검사")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    /** 회원가입  */
    @SignUpReqApi
    @SignUpResApi
    @PostMapping("/signup")
    public ResponseEntity<?> createMember(@RequestBody SignUpReqDto signUpReqDto){

        try{
            SignUpResDto signUpResDto = authService.createMember(signUpReqDto);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(BaseResponse.builder()
                            .message("요청에 성공하였습니다.")
                            .code(HttpStatus.CREATED.value())
                            .result(signUpResDto)
                            .build());
        }

        catch(MemberDuplicateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
    }
}
