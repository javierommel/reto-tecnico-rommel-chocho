package com.rommelchocho.ms_cuentas_movimientos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rommelchocho.ms_cuentas_movimientos.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findByNumeroCuentaAndFechaBetween(Long numeroCuenta, LocalDate fechaInicio, LocalDate fechaFin);

    Optional<Movimiento> findTopByNumeroCuentaOrderByFechaDesc(Long numeroCuenta);

}
