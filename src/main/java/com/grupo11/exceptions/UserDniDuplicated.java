package com.grupo11.exceptions;

public class UserDniDuplicated extends RuntimeException {
    public UserDniDuplicated(String message) {
        super(message);
    }
}
