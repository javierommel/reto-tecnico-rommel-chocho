package com.rommelchocho.ms_cuentas_movimientos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(String numeroCuenta) {
        super(String.format("Error movimiento: %s",numeroCuenta));
    }
}
