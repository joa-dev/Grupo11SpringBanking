package com.grupo11.exceptions;

public class InvestmentNotFoundException extends RuntimeException{
    public InvestmentNotFoundException(String message) {
        super(message);
    }
}