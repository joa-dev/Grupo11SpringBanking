package com.grupo11.exceptions;

public class TransferNotFoundException extends RuntimeException{
    public TransferNotFoundException(String message) {
        super(message);
    }
}