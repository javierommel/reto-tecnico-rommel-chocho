package com.rommelchocho.ms_cuentas_movimientos.mapper;

import com.rommelchocho.ms_cuentas_movimientos.dto.MovimientoDto;
import com.rommelchocho.ms_cuentas_movimientos.entity.Movimiento;

public class MovimientoMapper {

    public static MovimientoDto mapToMovimientoDto(Movimiento movimiento, MovimientoDto movimientoDto) {
        movimientoDto.setNumeroCuenta(movimiento.getNumeroCuenta());
        movimientoDto.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoDto.setValor(movimiento.getValor());
        movimientoDto.setId(movimiento.getId());
        movimientoDto.setFecha(movimiento.getFecha());
        return movimientoDto;
    }

    public static Movimiento mapToMovimiento(MovimientoDto movimientoDto, Movimiento movimiento) {
        movimiento.setNumeroCuenta(movimientoDto.getNumeroCuenta());
        movimiento.setTipoMovimiento(movimientoDto.getTipoMovimiento());
        movimiento.setValor(movimientoDto.getValor());
        return movimiento;
    }
}
