package com.rommelchocho.ms_cuentas_movimientos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rommelchocho.ms_cuentas_movimientos.dto.CuentaDto;
import com.rommelchocho.ms_cuentas_movimientos.entity.Cuenta;
import com.rommelchocho.ms_cuentas_movimientos.exception.CuentaExisteException;
import com.rommelchocho.ms_cuentas_movimientos.exception.ResourceNotFoundException;
import com.rommelchocho.ms_cuentas_movimientos.mapper.CuentaMapper;
import com.rommelchocho.ms_cuentas_movimientos.repository.ClienteRepository;
import com.rommelchocho.ms_cuentas_movimientos.repository.CuentaRepository;
import com.rommelchocho.ms_cuentas_movimientos.service.CuentaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CuentaServiceImpl implements CuentaService {
    
    private CuentaRepository cuentaRepository;
    private ClienteRepository clienteRepository;

    @Override
    public void createCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta= CuentaMapper.mapToCuenta(cuentaDto, new Cuenta());
        Optional<Cuenta> optionalCuenta = cuentaRepository.findByNumeroCuenta(cuentaDto.getNumeroCuenta());
        if (optionalCuenta.isPresent()) {
            throw new CuentaExisteException("Cuenta ya existe para el usuario"
                    +cuentaDto.getNumeroCuenta());
        }
        cuentaRepository.save(cuenta);
    }

    @Override
    public List<CuentaDto> getAllCuentas() {
        List<Cuenta> cuenta = (List<Cuenta>) cuentaRepository.findAll();
        return cuenta.stream().map(c -> CuentaMapper.mapToCuentaDto(c, new CuentaDto()))
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDto getCuentaByNumeroCuenta(Long numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta).orElseThrow(
                () -> new ResourceNotFoundException("Cuenta", "telefono", numeroCuenta.toString()));
        CuentaDto cuentaDto = CuentaMapper.mapToCuentaDto(cuenta, new CuentaDto());
        cuentaDto.setNombre(clienteRepository.findByClienteId(cuentaDto.getClienteId()).orElseThrow().getNombre());
        return cuentaDto;
    }

    @Override
    public boolean updateCuenta(Long numeroCuenta, CuentaDto cuentaDto) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta).orElseThrow(
                () -> new ResourceNotFoundException("Cliente", "numeroCuenta", numeroCuenta.toString()));
        CuentaMapper.mapToCuenta(cuentaDto, cuenta);
        cuenta = cuentaRepository.save(cuenta);
        return cuenta!=null;
    }

    @Override
    public boolean deleteCuenta(Long numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "numeroCuenta", numeroCuenta.toString())
        );
        cuentaRepository.deleteById(cuenta.getNumeroCuenta());
        return true;
    }

}
