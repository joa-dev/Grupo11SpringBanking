package com.grupo11.mappers;

import com.grupo11.entities.Account;
import com.grupo11.entities.dtos.AccountDto;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class AccountMapper {
    public /*static*/  AccountDto accountToDto(Account account){
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setType(account.getType());
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setAmount(account.getAmount());
        return dto;
    }
    public /*static*/ Account dtoToAccount(AccountDto dto){
        Account account = new Account();
        account.setAlias(dto.getAlias());
        account.setType(dto.getType());
        account.setCbu(dto.getCbu() );
        account.setAmount(dto.getAmount());
        return account;
    }
}
