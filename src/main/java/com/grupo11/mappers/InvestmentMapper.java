package com.grupo11.mappers;

import com.grupo11.entities.Investment;
import com.grupo11.entities.User;
import com.grupo11.entities.dtos.InvestmentDto;
import com.grupo11.entities.dtos.UserDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class InvestmentMapper {

    public static Investment dtoToInvestment(InvestmentDto dto){
        Investment investment = new Investment();
        investment.setBalance(dto.getBalance());
        investment.setAmount(dto.getAmount());
        investment.setStartDate(LocalDate.now());
        investment.setEndDate(dto.getEndDate());
        return investment;

    }

    public static InvestmentDto investmentToDto(Investment investment){
        InvestmentDto dto = new InvestmentDto();
        dto.setId(investment.getId());
        dto.setAccountId(investment.getAccount().getId());
        dto.setInvestmentInterest(investment.getInvestmentInterest());
        dto.setRetornsTime(investment.getRetornsTime());
        dto.setAmount(investment.getAmount());
        dto.setBalance(investment.getBalance());

        dto.setStartDate(investment.getStartDate());
        dto.setEndDate(investment.getEndDate());
        return dto;
    }

}
