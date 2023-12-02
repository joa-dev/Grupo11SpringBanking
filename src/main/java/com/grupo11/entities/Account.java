package com.grupo11.entities;

import com.grupo11.entities.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_cuenta")
    private AccountType type;
    private String cbu;
    private String alias;
    @Column(name = "monto")
    private BigDecimal amount;
    @Column(name = "fecha_creacion")
    private LocalDateTime created_at;
    @Column(name = "fecha_modificacion")
    private LocalDateTime updated_at;
    @ManyToOne
    @Column(name = "titular")
    private User owner;
   // @OneToMany(fetch = FetchType.EAGER,mappedBy = "transmitter",cascade=CascadeType.ALL, orphanRemoval = true)
   // private List<Transfer> transfers;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "investor",cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Investment> investments;
}
