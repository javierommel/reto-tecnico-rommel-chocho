package com.rommelchocho.ms_cuentas_movimientos.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.rommelchocho.ms_cuentas_movimientos.dto.MovimientoDto;
import com.rommelchocho.ms_cuentas_movimientos.entity.Cuenta;
import com.rommelchocho.ms_cuentas_movimientos.entity.Movimiento;
import com.rommelchocho.ms_cuentas_movimientos.exception.ResourceNotFoundException;
import com.rommelchocho.ms_cuentas_movimientos.mapper.MovimientoMapper;
import com.rommelchocho.ms_cuentas_movimientos.repository.CuentaRepository;
import com.rommelchocho.ms_cuentas_movimientos.repository.MovimientoRepository;
import com.rommelchocho.ms_cuentas_movimientos.service.MovimientoService;
import com.rommelchocho.ms_cuentas_movimientos.service.strategy.MovimientoStrategy;
import com.rommelchocho.ms_cuentas_movimientos.service.strategy.MovimientoStrategyFactory;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {
    
    private MovimientoRepository movimientoRepository;
    private CuentaRepository cuentaRepository;
    private MovimientoStrategyFactory movimientoStrategyFactory;

    @Override
    public void createMovimiento(MovimientoDto movimientoDto) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimientoDto.getNumeroCuenta())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta", "numeroCuenta", movimientoDto.getNumeroCuenta().toString()));
        Optional<Movimiento> ultimoMovimiento = movimientoRepository.findTopByNumeroCuentaOrderByFechaDesc(cuenta.getNumeroCuenta());

        Double saldoDisponible = ultimoMovimiento
                .map(Movimiento::getSaldo)
                .orElse(cuenta.getSaldoInicial());

        MovimientoStrategy strategy = movimientoStrategyFactory.getStrategy(movimientoDto.getTipoMovimiento());
        strategy.validar(saldoDisponible, movimientoDto.getValor());
        Double nuevoSaldoDisponible = strategy.calcularNuevoSaldo(saldoDisponible, movimientoDto.getValor());


        /*if ("Retiro".equalsIgnoreCase(movimientoDto.getTipoMovimiento()) && saldoDisponible + movimientoDto.getValor() < 0) {
            throw new SaldoInsuficienteException(cuenta.getNumeroCuenta().toString());
        }
        Double nuevoSaldoDisponible = saldoDisponible + movimientoDto.getValor();*/

        Movimiento movimiento=MovimientoMapper.mapToMovimiento(movimientoDto,new Movimiento());
        movimiento.setFecha(LocalDate.now());
        movimiento.setSaldo(nuevoSaldoDisponible);
        movimientoRepository.save(movimiento);
    }

    @Override
    public List<MovimientoDto> getAllMovimientos() {
        List<Movimiento> movimiento = (List<Movimiento>) movimientoRepository.findAll();
        return movimiento.stream().map(c -> MovimientoMapper.mapToMovimientoDto(c, new MovimientoDto()))
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoDto getMovimientoById(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Movimiento", "id", id.toString()));
        MovimientoDto movimientoDto = MovimientoMapper.mapToMovimientoDto(movimiento, new MovimientoDto());
        return movimientoDto;
    }

    @Override
    public boolean updateMovimiento(MovimientoDto movimientoDto) {
        boolean isUpdated = false;
        Movimiento movimiento=movimientoRepository.findById(movimientoDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Movimiento", "id", movimientoDto.getId().toString())
        );
        MovimientoMapper.mapToMovimiento(movimientoDto, movimiento);
        movimiento = movimientoRepository.save(movimiento);
        return isUpdated;
    }

    @Override
    public boolean deleteMovimiento(Long id) {
        movimientoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Movimiento", "id", id.toString())
        );
        movimientoRepository.deleteById(id);
        return true;
    }

}
