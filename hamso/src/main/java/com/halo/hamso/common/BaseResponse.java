package com.halo.hamso.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BaseResponse<T> {

    private String message;
    private int code;
    private T response;

    @Builder
    public BaseResponse(int code, String message,T result){
        this.message = message;
        this.code= code;
        this.response=result;
    }

    public BaseResponse(int code, String message){
        this.code =code;
        this.message=message;
    }
}
