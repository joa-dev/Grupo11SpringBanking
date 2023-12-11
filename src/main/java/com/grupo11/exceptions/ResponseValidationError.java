package com.grupo11.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseValidationError {
    private String error;
    private String message;
    private Map<String,String> details;

    public void setError(HttpStatus status) {
        this.error = "ERROR_" + status.name();
    }
}
