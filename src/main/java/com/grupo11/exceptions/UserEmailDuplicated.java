package com.grupo11.exceptions;

public class UserEmailDuplicated extends RuntimeException {
    public UserEmailDuplicated(String message) {
        super(message);
    }
}
