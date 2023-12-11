package com.grupo11.entities;

import com.grupo11.entities.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cuentas")
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
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
    @JoinColumn(name = "titular")
    private User owner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account",cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Investment> investments;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "origin",cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> transfersSent;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "target",cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> transfersReceived;


    public Account(){
        this.investments = new ArrayList<>();
        this.transfersSent = new ArrayList<>();
        this.transfersReceived = new ArrayList<>();
    }
}
