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
        dto.setCreated_at(account.getCreated_at());
        dto.setUpdated_at(account.getUpdated_at());
        return dto;
    }
    public /*static*/ Account dtoToAccount(AccountDto dto){
        Account account = new Account();
        account.setAlias(dto.getAlias());
        account.setType(dto.getType());
        account.setCbu(dto.getCbu() );
        account.setAmount(dto.getAmount());
        account.setCreated_at(LocalDateTime.now());
        account.setUpdated_at(LocalDateTime.now());
        return account;
    }
}
