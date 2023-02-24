package org.tlp.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForbiddenExceptionTest {

    @Test
    void whenExceptionIsCreated_shouldHaveMessage() {
        Exception exception = assertThrows(ForbiddenException.class, () -> {
            throw new ForbiddenException();
        });

        assertEquals("Operation not allowed", exception.getMessage());
    }
}