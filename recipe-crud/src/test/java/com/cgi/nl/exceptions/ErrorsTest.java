package com.cgi.nl.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ErrorsTest {

    @Test
    void testCanEqual() {
        assertFalse(
                (new Errors(1, "Technical issues occured while", LocalDate.of(2024, 1, 1).atStartOfDay())).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {

        Errors errors = new Errors(1, "Technical issues occured while", LocalDate.of(2024, 1, 1).atStartOfDay());

        assertTrue(errors.canEqual(new Errors(1, "Technical issues occured while", LocalDate.of(2024, 1, 1).atStartOfDay())));
    }

    @Test
    void testGettersAndSetters() {
        Errors actualErrors = new Errors(1, "Resourc Not found", LocalDate.of(2024, 1, 1).atStartOfDay());
        actualErrors.setMessage("Resource Not found");
        actualErrors.setStatus(1);
        LocalDateTime timeStamp = LocalDate.of(2024, 1, 1).atStartOfDay();
        actualErrors.setTimeStamp(timeStamp);
        String actualToStringResult = actualErrors.toString();
        String actualMessage = actualErrors.getMessage();
        int actualStatus = actualErrors.getStatus();
        assertEquals("Errors(status=1, message=Resource Not found, timeStamp=2024-01-01T00:00)",
                actualToStringResult);
        assertEquals("Resource Not found", actualMessage);
        assertEquals(1, actualStatus);
        assertSame(timeStamp, actualErrors.getTimeStamp());
    }
}
