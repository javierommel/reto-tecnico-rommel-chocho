package com.rommelchocho.ms_cuentas_movimientos.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rommelchocho.ms_cuentas_movimientos.dto.ReporteMovimientoDto;
import com.rommelchocho.ms_cuentas_movimientos.entity.Cuenta;
import com.rommelchocho.ms_cuentas_movimientos.entity.Movimiento;
import com.rommelchocho.ms_cuentas_movimientos.repository.ClienteRepository;
import com.rommelchocho.ms_cuentas_movimientos.repository.CuentaRepository;
import com.rommelchocho.ms_cuentas_movimientos.repository.MovimientoRepository;
import com.rommelchocho.ms_cuentas_movimientos.service.ReporteService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReporteServiceImpl implements ReporteService {
    
    private CuentaRepository cuentaRepository;
    private MovimientoRepository movimientoRepository;
    private ClienteRepository clienteReplicaRepository;


    public List<ReporteMovimientoDto> generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

        return cuentas.stream()
                .flatMap(cuenta -> {
                    
                    List<Movimiento> movimientos = movimientoRepository.findByNumeroCuentaAndFechaBetween(
                            cuenta.getNumeroCuenta(), fechaInicio, fechaFin);

                    return movimientos.stream().map(mov -> new ReporteMovimientoDto(
                            mov.getFecha().toString(),
                            clienteReplicaRepository.findByClienteId(clienteId).orElseThrow().getNombre(), 
                            cuenta.getNumeroCuenta(),
                            cuenta.getTipo(),
                            cuenta.getSaldoInicial(),
                            cuenta.getEstado(),
                            mov.getValor(),
                            mov.getSaldo() 
                    ));
                })
                .collect(Collectors.toList());
    }

}
