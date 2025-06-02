package com.rommelchocho.ms_cuentas_movimientos.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rommelchocho.ms_cuentas_movimientos.dto.ReporteMovimientoDto;
import com.rommelchocho.ms_cuentas_movimientos.service.ReporteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/reportes")
@AllArgsConstructor
public class ReporteController {
    
    
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteMovimientoDto>> generarReporte(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin,
            @RequestParam Long clienteId) { 

        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);

        List<ReporteMovimientoDto> reporte = reporteService.generarReporte(clienteId, inicio, fin);

        return ResponseEntity.ok(reporte);
    }
}
