package com.halo.hamso.dto.business;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessLoginDto {

    private String businessId;
    private String password;
}
