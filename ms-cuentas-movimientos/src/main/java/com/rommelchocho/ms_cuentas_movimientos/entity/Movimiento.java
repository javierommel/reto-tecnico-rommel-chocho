package com.rommelchocho.ms_cuentas_movimientos.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Movimiento {
    
    @Column(name="numero_cuenta")
    private Long numeroCuenta;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    private LocalDate fecha;

    @Column(nullable = false, name="tipo_movimiento")
    private String tipoMovimiento;
    
    private Double valor;
    
    private Double saldo;
    
    
}
