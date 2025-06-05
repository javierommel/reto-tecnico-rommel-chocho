package com.rommelchocho.ms_cuentas_movimientos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rommelchocho.ms_cuentas_movimientos.dto.CuentaDto;
import com.rommelchocho.ms_cuentas_movimientos.dto.ResponseDto;
import com.rommelchocho.ms_cuentas_movimientos.service.CuentaService;
import constants.CuentaConstants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cuentas")
@AllArgsConstructor
@Validated
public class CuentaController {

    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<ResponseDto> createCuenta(@Valid @RequestBody CuentaDto cuentaDto) {
        cuentaService.createCuenta(cuentaDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CuentaConstants.STATUS_201, CuentaConstants.MESSAGE_201));

    }

    @GetMapping
    public ResponseEntity<List<CuentaDto>> getAllCuentas() {
        return ResponseEntity.ok(cuentaService.getAllCuentas());
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaDto> getCuentaById(@PathVariable Long numeroCuenta) {
        CuentaDto cuentaDto = cuentaService.getCuentaByNumeroCuenta(numeroCuenta);
        return ResponseEntity.status(HttpStatus.OK).body(cuentaDto);
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<ResponseDto> updateCuenta(
            @PathVariable Long numeroCuenta,
            @Valid @RequestBody CuentaDto cuentaDto) {
        boolean isUpdated = cuentaService.updateCuenta(numeroCuenta, cuentaDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CuentaConstants.STATUS_200, CuentaConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CuentaConstants.STATUS_417, CuentaConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<ResponseDto> deleteCuenta(@PathVariable Long numeroCuenta) {
        boolean isDeleted = cuentaService.deleteCuenta(numeroCuenta);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CuentaConstants.STATUS_200, CuentaConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CuentaConstants.STATUS_417, CuentaConstants.MESSAGE_417_DELETE));
        }
    }
}
