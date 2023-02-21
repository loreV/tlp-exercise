package org.tlp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Can not find such item")
public class NotFoundItemException extends RuntimeException {
    public NotFoundItemException(){
        super("Could not find item");
    }
}
