package com.grupo11.entities.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.*;
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
    @NotNull(message = "El identificador de la cuenta no puede ser nulo")
    @Positive(message = "El identificador de la cuenta debe ser mayor que cero")
    private Long accountId;
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que 0")
    @NotNull(message = "El monto no puede ser nulo")
    private BigDecimal amount;

    private BigDecimal balance;

    private double investmentInterest;

    private Integer investmentPeriod;

    private LocalDate startDate;

    private LocalDate endDate;

}
