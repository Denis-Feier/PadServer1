package com.projectpad.server1.exception;

public class UserNameExistsException extends RuntimeException{
    public UserNameExistsException(String userName_exists) {
        super(userName_exists);
    }
}
