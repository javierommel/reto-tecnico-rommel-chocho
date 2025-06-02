package com.rommelchocho.ms_cuentas_movimientos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rommelchocho.ms_cuentas_movimientos.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long>{
    
    List<Cuenta> findByClienteId(Long clienteId);
    
    Optional<Cuenta> findByNumeroCuenta(Long numeroCuenta);
}
