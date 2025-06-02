package com.rommelchocho.ms_cuentas_movimientos.service.strategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class MovimientoStrategyFactory {
     private final Map<String, MovimientoStrategy> strategyMap;

    public MovimientoStrategyFactory(List<MovimientoStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(MovimientoStrategy::getTipo, s -> s));
    }

    public MovimientoStrategy getStrategy(String tipoMovimiento) {
        MovimientoStrategy strategy = strategyMap.get(tipoMovimiento);
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de movimiento no soportado: " + tipoMovimiento);
        }
        return strategy;
    }
}
