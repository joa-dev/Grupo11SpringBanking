package com.grupo11.entities.dtos;

import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotNull(message = "El id de origen no puede estar en blanco")
    private Long origin;
    @NotNull(message = "El id de destino no puede estar en blanco")
    private Long target;

    private LocalDateTime date;
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que 0")
    @NotNull(message = "El monto no puede ser nulo")
    private BigDecimal amount;
}
