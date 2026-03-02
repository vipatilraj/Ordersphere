package com.example.ordersphere.exception;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String message){
        super(message);
    }

}
