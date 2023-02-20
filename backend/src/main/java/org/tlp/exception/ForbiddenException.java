package org.tlp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.METHOD_NOT_ALLOWED, reason="Can not execute such method")
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(){
        super("Operation not allowed");
    }
}
