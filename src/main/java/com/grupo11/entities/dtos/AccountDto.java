package com.grupo11.entities.dtos;

import com.grupo11.entities.enums.AccountType;
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
    private AccountType type;
    private String name;
    private String cbu;
    private String alias;
    private BigDecimal amount;


}
