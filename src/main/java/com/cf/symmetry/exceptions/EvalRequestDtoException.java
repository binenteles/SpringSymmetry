package com.cf.symmetry.exceptions;

public class EvalRequestDtoException extends RuntimeException{
    public EvalRequestDtoException(String message) {
        super(message);
    }

    public EvalRequestDtoException(String message, Throwable cause) {
        super(message, cause);
    }
}
