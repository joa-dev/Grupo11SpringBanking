package com.grupo11.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalErrorHandler {
    /*2)Manejo de Excepciones:
    Mejorar el manejo de excepciones personalizando los mensajes de error
    y retornando códigos de estado HTTP apropiados.*/
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> manejarAccountNotFoundException(AccountNotFoundException exception, WebRequest webRequest) {
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.NOT_FOUND);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsuficientFoundsException.class)
    public ResponseEntity<?> manejarInsuficientFoundsException(InsuficientFoundsException exception, WebRequest webRequest) {
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.BAD_REQUEST);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferNotFoundException.class)
    public ResponseEntity<?> manejarTransferNotFoundException(TransferNotFoundException exception, WebRequest webRequest) {
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.NOT_FOUND);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvestmentNotFoundException.class)
    public ResponseEntity<?> manejarInvestmentNotFoundException(InvestmentNotFoundException exception, WebRequest webRequest) {
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.NOT_FOUND);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NegativeInvestmentException.class)
    public ResponseEntity<?> manejarNegativeInvestment(NegativeInvestmentException exception, WebRequest webRequest) {
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.BAD_REQUEST);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> manejarUserNotFoundException(UserNotFoundException exception, WebRequest webRequest) {
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.NOT_FOUND);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserEmailDuplicated.class)
    public ResponseEntity<?> manejarUserEmailDuplicated(UserEmailDuplicated exception, WebRequest webRequest) {
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.BAD_REQUEST);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDniDuplicated.class)
    public ResponseEntity<?> manejarUserDniDuplicated(UserDniDuplicated exception, WebRequest webRequest) {
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.BAD_REQUEST);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object>manejarValidationErrors(MethodArgumentNotValidException error){
        //Definición del map para guardar: Campo - Descripcion error
        Map<String,String> errores= new HashMap<>();

        //Recorrer los errores y agregar a map
        error.getBindingResult().getAllErrors().forEach((e)->{
            String nombreCampo= ((FieldError)e).getField();
            String mensaje=e.getDefaultMessage();

            errores.put(nombreCampo,mensaje);
        });

        //Generar Objeto de respuesta y retornarlo
        ResponseValidationError listaErrores= new ResponseValidationError();
        listaErrores.setError(HttpStatus.BAD_REQUEST);
        listaErrores.setMessage("Revise los siguientes datos:");
        listaErrores.setDetails(errores);
        return new ResponseEntity<>(listaErrores,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> manejarHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest webRequest) {
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.BAD_REQUEST);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
