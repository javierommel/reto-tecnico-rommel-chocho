package com.rommelchocho.ms_cuentas_movimientos.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimientoDto {

    private Long id;  

    @NotNull(message = "Valor no puede ser null o vacío")
    private Double valor;  
    
    @NotEmpty(message = "Tipo Movimiento no puede ser null o vacío")
    private String tipoMovimiento;
    
    private LocalDate fecha;

    @NotNull(message = "Numero Cuenta no puede ser null o vacío")
    private Long numeroCuenta;
    
    private String tipo;
    
    private Double saldoInicial;  
    
    private Boolean estado;
    
}

