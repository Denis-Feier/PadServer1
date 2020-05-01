package com.projectpad.server1.exception;

public class UserTokenNotFound extends RuntimeException{
    public UserTokenNotFound(String e) {
        super(e);
    }
}
