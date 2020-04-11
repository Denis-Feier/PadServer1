package com.projectpad.server1.exception;

public class UserEmailExistsException extends RuntimeException {
    public UserEmailExistsException(String email_exists) {
        super(email_exists);
    }
}
