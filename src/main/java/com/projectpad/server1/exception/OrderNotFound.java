package com.projectpad.server1.exception;

public class OrderNotFound extends RuntimeException{
    public OrderNotFound(String s) {
        super(s);
    }
}
