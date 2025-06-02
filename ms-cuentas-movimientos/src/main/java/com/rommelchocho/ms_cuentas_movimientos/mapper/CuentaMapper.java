package com.rommelchocho.ms_cuentas_movimientos.mapper;

import com.rommelchocho.ms_cuentas_movimientos.dto.CuentaDto;
import com.rommelchocho.ms_cuentas_movimientos.entity.Cuenta;


public class CuentaMapper {

    public static CuentaDto mapToCuentaDto(Cuenta cuenta, CuentaDto cuentaDto) {
        cuentaDto.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDto.setEstado(cuenta.getEstado());
        cuentaDto.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaDto.setTipo(cuenta.getTipo());
        cuentaDto.setClienteId(cuenta.getClienteId());
        return cuentaDto;
    }

    public static Cuenta mapToCuenta(CuentaDto cuentaDto, Cuenta cuenta) {
        cuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
        cuenta.setEstado(cuentaDto.getEstado());
        cuenta.setSaldoInicial(cuentaDto.getSaldoInicial());
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setClienteId(cuentaDto.getClienteId());
        return cuenta;
    }
}
