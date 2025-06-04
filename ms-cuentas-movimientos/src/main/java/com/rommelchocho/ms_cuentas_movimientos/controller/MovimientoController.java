package com.rommelchocho.ms_cuentas_movimientos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rommelchocho.ms_cuentas_movimientos.dto.MovimientoDto;
import com.rommelchocho.ms_cuentas_movimientos.dto.ResponseDto;
import com.rommelchocho.ms_cuentas_movimientos.service.MovimientoService;
import constants.MovimientoConstants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/movimientos")
@AllArgsConstructor
@Validated
public class MovimientoController {
    
    
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<ResponseDto> createMovimiento(@RequestBody MovimientoDto movimientoDto) {
        movimientoService.createMovimiento(movimientoDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(MovimientoConstants.STATUS_201, MovimientoConstants.MESSAGE_201));
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDto>> getAllMovimientos() {
        return ResponseEntity.ok(movimientoService.getAllMovimientos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDto> getMovimientoById(@RequestParam Long id) {
        MovimientoDto movimientoDto = movimientoService.getMovimientoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(movimientoDto);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateMovimiento(@Valid @RequestBody MovimientoDto movimientoDto) {
        boolean isUpdated = movimientoService.updateMovimiento(movimientoDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(MovimientoConstants.STATUS_200, MovimientoConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(MovimientoConstants.STATUS_417, MovimientoConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteMovimiento(@RequestParam Long id) {
        boolean isDeleted = movimientoService.deleteMovimiento(id);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(MovimientoConstants.STATUS_200, MovimientoConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(MovimientoConstants.STATUS_417, MovimientoConstants.MESSAGE_417_DELETE));
        }
    }

}
