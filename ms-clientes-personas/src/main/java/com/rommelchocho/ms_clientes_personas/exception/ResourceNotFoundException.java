package com.rommelchocho.ms_clientes_personas.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String recurso, String campo, String valor ) {
        super(String.format("%s no encontrado con los datos ingresados %s: '%s'",recurso, campo, valor));
    }
}
