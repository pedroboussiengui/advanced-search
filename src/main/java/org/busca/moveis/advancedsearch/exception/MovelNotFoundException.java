package org.busca.moveis.advancedsearch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovelNotFoundException extends RuntimeException {

    public MovelNotFoundException(String message) {
        super(message);
    }

    public MovelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
