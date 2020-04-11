package com.projectpad.server1.exception;

public class UserCredentialsInvalidException extends RuntimeException{
    public UserCredentialsInvalidException(String inv) {
        super(inv);
    }
}
