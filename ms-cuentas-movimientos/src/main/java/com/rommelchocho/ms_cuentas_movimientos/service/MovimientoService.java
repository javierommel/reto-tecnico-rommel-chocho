package com.rommelchocho.ms_cuentas_movimientos.service;

import java.util.List;
import com.rommelchocho.ms_cuentas_movimientos.dto.MovimientoDto;

public interface MovimientoService {

    void createMovimiento(MovimientoDto movimientoDto);

    List<MovimientoDto> getAllMovimientos();

    MovimientoDto getMovimientoById(Long id);

    boolean updateMovimiento(Long id, MovimientoDto movimientoDto);

    boolean deleteMovimiento(Long id);
}
