package org.tlp.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotFoundItemExceptionTest {

    @Test
    void whenExceptionIsCreated_shouldHaveMessage() {
        Exception exception = assertThrows(NotFoundItemException.class, () -> {
            throw new NotFoundItemException();
        });

        assertEquals("Could not find item", exception.getMessage());
    }
}