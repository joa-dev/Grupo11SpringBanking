package com.grupo11.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobarErrorHandler {
    /*2)Manejo de Excepciones:
    Mejorar el manejo de excepciones personalizando los mensajes de error
    y retornando códigos de estado HTTP apropiados.*/
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> manejarAccountNotFoundException(AccountNotFoundException exception, WebRequest webRequest){
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.NOT_FOUND);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsuficientFoundsException.class)
    public ResponseEntity<?> manejarInsuficientFoundsException(InsuficientFoundsException exception, WebRequest webRequest){
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.BAD_REQUEST);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferNotFoundException.class)
    public ResponseEntity<?> manejarTransferNotFoundException(TransferNotFoundException exception, WebRequest webRequest){
        ResponseExceptionEntity response = new ResponseExceptionEntity();
        response.setError(HttpStatus.NOT_FOUND);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
