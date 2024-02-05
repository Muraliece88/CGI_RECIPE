package com.cgi.nl.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.nio.file.AccessDeniedException;
import java.util.HashSet;
import javax.naming.AuthenticationException;

import org.apache.coyote.BadRequestException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

class HandlersTest {

    @Test
    void testHandleMethodAugumentInvalid() {

        Handlers handlers = new Handlers();
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        when(beanPropertyBindingResult.getFieldError())
                .thenReturn(new FieldError("Object Name", "Field", "Default Message"));
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(beanPropertyBindingResult);
        when(ex.getMessage()).thenReturn("An error occurred");
        ResponseEntity<Errors> actualHandleMethodAugumentInvalidResult = handlers.handleMethodAugumentInvalid(ex);
        verify(beanPropertyBindingResult).getFieldError();
        verify(ex).getBindingResult();
        verify(ex).getMessage();
        Errors body = actualHandleMethodAugumentInvalidResult.getBody();
        assertEquals("Default Message", body.getMessage());
        assertEquals(400, body.getStatus());
        assertEquals(400, actualHandleMethodAugumentInvalidResult.getStatusCodeValue());
        assertTrue(actualHandleMethodAugumentInvalidResult.hasBody());
        assertTrue(actualHandleMethodAugumentInvalidResult.getHeaders().isEmpty());
    }

    @Test
    void testHandleBadrequest() {

        Handlers handlers = new Handlers();
        ResponseEntity<Errors> actualHandleBadrequestResult = handlers
                .handleBadrequest(new BadRequestException("An error occurred"));
        Errors body = actualHandleBadrequestResult.getBody();
        assertEquals("An error occurred", body.getMessage());
        assertEquals(400, body.getStatus());
        assertEquals(400, actualHandleBadrequestResult.getStatusCodeValue());
        assertTrue(actualHandleBadrequestResult.hasBody());
        assertTrue(actualHandleBadrequestResult.getHeaders().isEmpty());
    }

    @Test
    void testHandleConstraintViolation() {
        Handlers handlers = new Handlers();
        ConstraintViolation<Object> constraintViolation = mock(ConstraintViolation.class);
        when(constraintViolation.getPropertyPath()).thenReturn(PathImpl.createRootPath());
        when(constraintViolation.getMessage()).thenReturn("something violated");
        HashSet<ConstraintViolation<?>> constraintViolations = new HashSet<>();
        constraintViolations.add(constraintViolation);
        ResponseEntity<Errors> actualHandleConstraintViolationResult = handlers
                .handleConstraintViolation(new ConstraintViolationException(constraintViolations));
        verify(constraintViolation, atLeast(1)).getMessage();
        verify(constraintViolation).getPropertyPath();
        Errors body = actualHandleConstraintViolationResult.getBody();
        assertEquals("something violated", body.getMessage());
        assertEquals(400, body.getStatus());
        assertEquals(400, actualHandleConstraintViolationResult.getStatusCodeValue());

    }
    @Test
    void testHandleUnauthException() {
        Handlers handlers = new Handlers();
        ResponseEntity<Errors> actualHandleUnauthExceptionResult = handlers
                .handleUnauthException(new AuthenticationException("foo"));
        Errors body = actualHandleUnauthExceptionResult.getBody();
        assertEquals("Invalid credentials supplied", body.getMessage());
        assertEquals(401, body.getStatus());
        assertEquals(401, actualHandleUnauthExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleUnauthExceptionResult.hasBody());
        assertTrue(actualHandleUnauthExceptionResult.getHeaders().isEmpty());
    }
    @Test
    void testHandleAccessException() {
        Handlers handlers = new Handlers();
        ResponseEntity<Errors> actualHandleAccessExceptionResult = handlers
                .handleAccessException(new AccessDeniedException("denied"));
        Errors body = actualHandleAccessExceptionResult.getBody();
        assertEquals("Insufficient rights to perform the opration", body.getMessage());
        assertEquals(403, body.getStatus());
        assertEquals(403, actualHandleAccessExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleAccessExceptionResult.hasBody());
        assertTrue(actualHandleAccessExceptionResult.getHeaders().isEmpty());
    }

    @Test
    void testHandleOtherException() {
        Handlers handlers = new Handlers();
        ResponseEntity<Errors> actualHandleOtherExceptionResult = handlers.handleOtherException(new Exception("ex occured"));
        Errors body = actualHandleOtherExceptionResult.getBody();
        assertEquals("Technical issues when operation is performed", body.getMessage());
        assertEquals(403, actualHandleOtherExceptionResult.getStatusCodeValue());
        assertEquals(500, body.getStatus());
        assertTrue(actualHandleOtherExceptionResult.hasBody());
        assertTrue(actualHandleOtherExceptionResult.getHeaders().isEmpty());
    }
}
