package com.grupo11.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentDto {

    private long id;

    //private Account account; Falta cuenta

    private BigDecimal amount;

    private double investmentInterest;

    private int retornsTime;

    private LocalDate startDate;

    private LocalDate endDate;

}
