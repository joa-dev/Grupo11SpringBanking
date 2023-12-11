package com.grupo11.mappers;

import com.grupo11.entities.Account;
import com.grupo11.entities.dtos.AccountDto;
import com.grupo11.entities.dtos.InvestmentDto;
import com.grupo11.entities.dtos.TransferDto;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class AccountMapper {
    public /*static*/  AccountDto accountToDto(Account account){
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setUserId(account.getOwner().getId());
        dto.setUserName(account.getOwner().getUsername());
        dto.setType(account.getType());
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setAmount(account.getAmount());

        List<InvestmentDto> investments = account.getInvestments().stream().map( i -> InvestmentMapper.investmentToDto(i)).collect(Collectors.toList());
        dto.setInvestments(investments);

        List<TransferDto> transfers = account.getTransfersSent().stream().map(t -> TransferMapper.transferToDto(t)).collect(Collectors.toList());
        dto.setTransfersSent(transfers);

        List<TransferDto> transfersReceived = account.getTransfersReceived().stream().map(t -> TransferMapper.transferToDto(t)).collect(Collectors.toList());
        dto.setTransfersReceived(transfersReceived);

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
