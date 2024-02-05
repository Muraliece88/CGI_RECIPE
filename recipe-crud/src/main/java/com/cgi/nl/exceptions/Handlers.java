package com.cgi.nl.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;


@RestControllerAdvice(basePackages ="com.cgi.nl")
@Slf4j

public class Handlers{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Errors> handleMethodAugumentInvalid(MethodArgumentNotValidException ex) {
        log.error("exception occured"+ex.getMessage());
        Errors errors=new Errors(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getFieldError().getDefaultMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errors);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Errors> handleitemNnotavail(ResourceNotFoundException ex) {
        log.error("exception occured"+ex.getMessage());
        Errors errors=new Errors(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errors);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Errors> handleBadrequest(BadRequestException ex) {
        log.error("exception occured"+ex.getMessage());
        Errors errors=new Errors(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errors);

    }

    @ExceptionHandler(ConstraintViolationException.class)

    public ResponseEntity<Errors> handleConstraintViolation(ConstraintViolationException ex) {
        log.error("exception occured"+ex.getMessage());
        Errors errors=new Errors(HttpStatus.BAD_REQUEST.value(),ex.getConstraintViolations().stream().iterator().next().getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errors);

    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Errors> handleUnauthException(AuthenticationException ex) {
        log.error("exception occured"+ex.getMessage());
        Errors errors=new Errors(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials supplied", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(errors);


    }
    @ExceptionHandler(AccessDeniedException.class)

    public ResponseEntity<Errors> handleAccessException(AccessDeniedException ex) {
        log.error("exception occured"+ex.getMessage());
        Errors errors=new Errors(HttpStatus.FORBIDDEN.value(), "Insufficient rights to perform the opration", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(errors);

    }
   @ExceptionHandler(Exception.class)

    public ResponseEntity<Errors> handleOtherException(Exception ex) {

       log.error("exception occured"+ex.getMessage());
       Errors errors=new Errors(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Technical issues when operation is performed", LocalDateTime.now());
       return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(errors);


    }
}
