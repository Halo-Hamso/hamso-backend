package com.halo.hamso.controller;


import com.halo.hamso.common.exception.InvalidPasswordException;
import com.halo.hamso.common.exception.MemberDuplicateException;
import com.halo.hamso.dto.member.find.FindPwdReqDto;
import com.halo.hamso.dto.member.login.LoginReqDto;
import com.halo.hamso.dto.member.login.LoginResDto;
import com.halo.hamso.dto.member.signup.SignUpReqDto;
import com.halo.hamso.dto.member.signup.SignUpResDto;
import com.halo.hamso.service.AuthService;
import com.halo.hamso.utils.swagger.Auth.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

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
                    .body(signUpResDto);
        }

        catch(MemberDuplicateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }


    /** 로그인 */
    @LoginReqApi
    @LoginResApi
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto memberInfo) {
        try {
            LoginResDto loginResDto = authService.login(memberInfo);
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

    /** 비밀번호 찾기 */

    @FindReqApi
    @FindResApi
    @PostMapping("/find-pwd")
    public ResponseEntity<?> findAndUpdatePassword(@RequestBody FindPwdReqDto pwdReqDtoDto) {
        try {
            String message = authService.findAndUpdatePassword(pwdReqDtoDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(message);
        }
        catch(NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
