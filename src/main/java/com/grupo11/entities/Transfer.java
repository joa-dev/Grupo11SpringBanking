package com.grupo11.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id", referencedColumnName = "id")
    private Account origin;

    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id", referencedColumnName = "id")
    private Account target;

    private LocalDateTime date;

    private BigDecimal amount;
}
