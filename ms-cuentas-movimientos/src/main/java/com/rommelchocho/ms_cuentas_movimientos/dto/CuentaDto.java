package com.rommelchocho.ms_cuentas_movimientos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CuentaDto {

    @NotNull(message = "Numero de Cuenta no puede ser null or vacío")
    private Long numeroCuenta;

    @NotEmpty(message = "Tipo de Cuenta no puede ser null or vacío")
    private String tipo;

    @NotNull(message = "Saldo Inicial de Cuenta no puede ser null or vacío")
    private Double saldoInicial;

    @NotNull(message = "Estado de Cuenta no puede ser null or vacío")
    private Boolean estado;

    @NotNull(message = "ClienteiD de Cuenta no puede ser null or vacío")
    private Long clienteId;

    private String nombre;
}

