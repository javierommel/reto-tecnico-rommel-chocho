package com.rommelchocho.ms_cuentas_movimientos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteSyncMessage {
    private Long clienteId;
    private String nombre;
}