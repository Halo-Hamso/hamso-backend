package com.halo.hamso.dto.business;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessSignUpReqDto {
    private String name;
    private String businessId;
    private String password;
    private String phoneNo;
    private String businessName;
    private String businessNo;
    private String businessType;
    private String businessCategory;
}
