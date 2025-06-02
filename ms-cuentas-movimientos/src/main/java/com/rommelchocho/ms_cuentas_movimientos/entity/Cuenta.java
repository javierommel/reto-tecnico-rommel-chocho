package com.rommelchocho.ms_cuentas_movimientos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cuenta {
    @Id
    @Column(name="numero_cuenta")
    private Long numeroCuenta;

    private String tipo;

    @Column(name="saldo_inicial")
    private Double saldoInicial;
    
    private Boolean estado;
    
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
}
