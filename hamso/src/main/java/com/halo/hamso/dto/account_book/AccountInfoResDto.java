package com.halo.hamso.dto.account_book;


import com.halo.hamso.repository.account_info.AccountInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoResDto {

    private String name;

    private String visitedTo;

    private String team;

    private String relation;

    private Integer money;

    public AccountInfoResDto(AccountInfo accountInfo) {
        this.name = accountInfo.getName();
        this.visitedTo = accountInfo.getVisitedTo();
        this.team = accountInfo.getTeam();
        this.relation = accountInfo.getRelation();
        this.money = accountInfo.getMoney();
    }
}
