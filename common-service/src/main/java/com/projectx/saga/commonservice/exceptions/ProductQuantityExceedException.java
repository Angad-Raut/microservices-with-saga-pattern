package com.projectx.saga.commonservice.exceptions;

public class ProductQuantityExceedException extends RuntimeException {
    public ProductQuantityExceedException(String msg) {
        super(msg);
    }
}
