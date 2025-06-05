package com.rommelchocho.ms_cuentas_movimientos.service.strategy;

import org.springframework.stereotype.Component;

import com.rommelchocho.ms_cuentas_movimientos.exception.SaldoInsuficienteException;

@Component
public class RetiroStrategy implements MovimientoStrategy{
@Override
    public String getTipo() {
        return "Retiro";
    }

    @Override
    public Double calcularNuevoSaldo(Double saldoActual, Double valorMovimiento) {
        return saldoActual - Math.abs(valorMovimiento);
    }

    @Override
    public void validar(Double saldoActual, Double valorMovimiento, Long numeroCuenta) {
        if (saldoActual < Math.abs(valorMovimiento)) {
            throw new SaldoInsuficienteException(numeroCuenta.toString());
        }
    }
}
