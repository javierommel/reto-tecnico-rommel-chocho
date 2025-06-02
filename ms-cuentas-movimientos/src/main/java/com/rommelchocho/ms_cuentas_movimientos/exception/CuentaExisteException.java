package com.rommelchocho.ms_cuentas_movimientos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CuentaExisteException extends RuntimeException{

    public CuentaExisteException(String message) {
        super(message);
    }

}

