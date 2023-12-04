package com.grupo11.entities.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransferDto {
    private Long id;

    private Long origin;

    private Long target;

    private LocalDateTime date;

    private BigDecimal amount;
}
