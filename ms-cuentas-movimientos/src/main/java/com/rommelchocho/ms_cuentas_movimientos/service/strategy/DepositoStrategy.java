package com.rommelchocho.ms_cuentas_movimientos.service.strategy;

import org.springframework.stereotype.Component;

@Component
public class DepositoStrategy implements MovimientoStrategy{
    @Override
    public String getTipo() {
        return "Deposito";
    }

    @Override
    public Double calcularNuevoSaldo(Double saldoActual, Double valorMovimiento) {
        return saldoActual + Math.abs(valorMovimiento);
    }

    @Override
    public void validar(Double saldoActual, Double valorMovimiento) {
        // No se requiere validación especial
    }
}
