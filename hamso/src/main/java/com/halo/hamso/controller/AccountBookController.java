package com.halo.hamso.controller;


import com.halo.hamso.dto.account_book.AccountInfoReqDto;
import com.halo.hamso.service.AccountBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.constraints.Min;


@RestController
@RequiredArgsConstructor
@RequestMapping("/account-book")
public class AccountBookController {

    private final AccountBookService accountBookService;

    /**  데이터 입력 받기   */
//    public ResponseEntity<?> registerInfo(@RequestBody AccountInfoReqDto accountInfoReqDto){
//
//    }



    /**  조문객 부의금 정보 전달 ( 페이징 ) */

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountBook(@PathVariable("id") Long id,
                                            @RequestParam("page") @Min(0) int page,
                                            @RequestParam("size") @Min(0) int size,
                                            @RequestParam(required = false,name = "searchKeyword") String searchKeyword)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(accountBookService.getAccountBook(id,page,size,searchKeyword));
        }
        catch(NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}
