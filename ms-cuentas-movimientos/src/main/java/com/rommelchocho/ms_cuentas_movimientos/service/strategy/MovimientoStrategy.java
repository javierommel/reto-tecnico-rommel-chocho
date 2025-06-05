package com.rommelchocho.ms_cuentas_movimientos.service.strategy;

public interface MovimientoStrategy {
    String getTipo();
    Double calcularNuevoSaldo(Double saldoActual, Double valorMovimiento);
    void validar(Double saldoActual, Double valorMovimiento, Long numeroCuenta);
}
