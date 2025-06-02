package com.rommelchocho.ms_cuentas_movimientos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cliente {
    
    @Id
    @Column(name="cliente_id")
    private Long clienteId;

    private String nombre;
}
