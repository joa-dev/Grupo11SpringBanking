package com.grupo11.exceptions;

public class NegativeInvestmentException extends RuntimeException{
    public NegativeInvestmentException(String message) {
        super(message);
    }
}