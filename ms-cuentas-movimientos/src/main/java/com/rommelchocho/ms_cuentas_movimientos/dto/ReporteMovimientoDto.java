package com.rommelchocho.ms_cuentas_movimientos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteMovimientoDto {

    private String fecha;           
    private String cliente;         
    private Long numeroCuenta;    
    private String tipo;            
    private Double saldoInicial;    
    private Boolean estado;         
    private Double movimiento;      
    private Double saldoDisponible; 
}