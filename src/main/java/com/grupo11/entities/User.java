package com.grupo11.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    private String email;

    private String dni;

    @Column(name = "address")
    private String address;

    @Column(name = "birthdate")
    private Date birthdate;

    // Fecha de creación y modificación (estado) de cada usuarios
    // TODO: Refactor
    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    //@OneToMany(mappedBy = "owner", cascade=CascadeType.ALL, orphanRemoval = true)
    //private List<Account> accounts;
}