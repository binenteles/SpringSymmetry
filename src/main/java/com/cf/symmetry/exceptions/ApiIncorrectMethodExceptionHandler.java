package com.cf.symmetry.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiIncorrectMethodExceptionHandler {

  @ExceptionHandler(value = {HttpMessageNotReadableException.class})
  public ResponseEntity<String> handleMethodRequestException(HttpMessageNotReadableException e) {
    return new ResponseEntity<>(
        "Inserted method is not correct! Options are: FOR, STACK, WHILE, REGEX",
        HttpStatus.BAD_REQUEST);
  }
}
