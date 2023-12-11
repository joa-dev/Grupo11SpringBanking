package com.grupo11.entities.dtos;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    @NotEmpty(message = "Debe completar el nombre del usuario.")
    private String username;
    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
    @NotEmpty(message = "El correo electrónico no puede estar vacío")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    private String email;
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    private String dni;
    @NotEmpty(message = "La dirección no puede estar vacía")
    private String address;
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    private Date birthdate;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}