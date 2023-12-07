package com.grupo11.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/*7)Formatos de Respuestas HTTP:
    Se deberá responder en cada Controller con una respuesta HTTP dependiendo de si la respuesta fue exitosa o no,
     en caso de fallo, mostrar error con el siguiente  formato de ejemplo:
     { error: “ERROR_NOT_FOUND”, message: “Usuario no encontrado”}*/
public class ResponseExceptionEntity {
    private String error;
    private String message;

    public void setError(HttpStatus status) {
        this.error = "ERROR_" + status.name();
    }
}
