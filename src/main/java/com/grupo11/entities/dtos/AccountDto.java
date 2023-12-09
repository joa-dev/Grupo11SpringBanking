package com.grupo11.entities.dtos;

import com.grupo11.entities.enums.AccountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    @NotNull(message="Especifique un tipo de cuenta")
    private AccountType type;
   // @Size(min = 22, max = 22, message="El cbu debe tener 22 números")
    @Pattern(regexp = "[0-9]{22}", message = "El cbu debe tener 22 números")
    @NotEmpty(message = "Un cbu es necesario")
    private String cb
    @NotEmpty(message = "El alias no puede estar en blanco")
    @Pattern(regexp = "[a-zA-Z0-9.]{3,20}", message = "El alias debe tener entre 3 y 20 caracteres y solo puede contener letras, números o puntos")
    private String alias;
    private BigDecimal amount;


}
