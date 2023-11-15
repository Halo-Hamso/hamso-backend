package com.halo.hamso.controller;


import com.halo.hamso.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@Tag(name = "관리자 Api", description = "이미지를 보고 직접 데이터 입력하기")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/bills")
    public ResponseEntity<?> getBillImage(@RequestParam("page") @Min(0) int page,
                                          @RequestParam("size") @Min(0) int size){
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminService.getBillImages(page, size));
    }

}
