package com.rommelchocho.ms_cuentas_movimientos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class ResponseDto {
    private String statusCode;
    private String statusMsg;
}
