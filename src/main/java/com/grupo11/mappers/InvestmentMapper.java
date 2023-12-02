package com.grupo11.mappers;

import com.grupo11.entities.Investment;
import com.grupo11.entities.User;
import com.grupo11.entities.dtos.InvestmentDto;
import com.grupo11.entities.dtos.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InvestmentMapper {

    public static Investment dtoToInvestment(InvestmentDto dto){
        Investment investment = new Investment();
        investment.setAmount(dto.getAmount());
        investment.setStartDate(LocalDate.now());
        return investment;
    }

    public static InvestmentDto investmentToDto(Investment investment){
        InvestmentDto dto = new InvestmentDto();
        dto.setAmount(investment.getAmount());
        dto.setStartDate(investment.getStartDate());
        return dto;
    }

}
