package com.halo.hamso.controller;


import com.halo.hamso.dto.account_book.AccountInfoReqDto;
import com.halo.hamso.dto.account_book.BillInfoReqDto;
import com.halo.hamso.dto.account_book.BillInfoResDto;
import com.halo.hamso.service.AccountBookService;
import com.halo.hamso.utils.swagger.account.AccountInfosReqApi;
import com.halo.hamso.utils.swagger.account.AccountInfosResApi;
import com.halo.hamso.utils.swagger.account.RegisterReqApi;
import com.halo.hamso.utils.swagger.account.RegisterResApi;
import com.halo.hamso.utils.swagger.statistic.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Date;

@Tag(name = "부의금 API", description = "부의금 등록, 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/account-book")
public class AccountBookController {

    private final AccountBookService accountBookService;

    /**  데이터 입력 받기   */
    @RegisterResApi
    @RegisterReqApi
    @PostMapping("/account-info/{id}")
    public ResponseEntity<?> registerInfo(@PathVariable("id") Long id, @RequestBody AccountInfoReqDto accountInfoReqDto){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(accountBookService.registerInfo(id, accountInfoReqDto));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }



    /**  부의금 가계부 기능 */

    @AccountInfosReqApi
    @AccountInfosResApi
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

    /** 부의금 가족, 방문 유족 통계 */
    @ReqApi1
    @ResApi1
    @GetMapping("/statistics/{id}")
    public ResponseEntity<?> getStatistics(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountBookService.getStatistics(id));
    }

    /** 용품별 결제 내역 */
    @ReqApi2
    @ResApi2
    @GetMapping("/bill/item")
    public ResponseEntity<?> getBillByItem(@RequestParam("page") @Min(0) int page,
                                           @RequestParam("size") @Min(0) int size){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountBookService.getBillByItem(page,size));
    }


    /** 실시간 결제 내역 */
    @ReqApi3
    @ResApi3
    @GetMapping("/bill/use-time")
    public ResponseEntity<?> getBillByUseTime(@RequestParam("page") @Min(0) int page,
                                              @RequestParam("size") @Min(0) int size){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountBookService.getBillByUseTime(page,size));
    }

    @ReqApi4
    @ResApi4
    @GetMapping("/chart")
    public ResponseEntity<?> getAccountByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                              @RequestParam("option") Integer option){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountBookService.getAccountByDateTime(date,option));
    }

    /**  견적서 데이터 입력받기  */
    @PostMapping("/bill-info/{id}")
    public ResponseEntity<?> registerBillInfo(@PathVariable("id") Long id, @RequestBody BillInfoReqDto billInfoReqDto) {

        try{
            BillInfoResDto billInfoResDto = accountBookService.billRegisterInfo(id, billInfoReqDto);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(billInfoResDto);
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

}
