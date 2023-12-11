package com.grupo11.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Inversion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Account account;

    @Column(name = "monto")
    private BigDecimal amount;

    @Column(name="saldo")
    private BigDecimal balance;

    @Column(name = "interes")
    private  double investmentInterest;

    @Column(name="periodo_inversion")
    private  Integer investmentPeriod; // dias para el retorno

    @Column(name = "fecha_creacion")
    private LocalDate startDate;

    @Column(name = "fecha_cierre")
    private LocalDate endDate;

}
