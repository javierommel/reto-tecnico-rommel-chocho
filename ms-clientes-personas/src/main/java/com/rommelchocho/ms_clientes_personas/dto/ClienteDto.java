package com.rommelchocho.ms_clientes_personas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDto {

    @NotNull(message = "Estado no puede ser null o vacío")
    private Boolean estado;

    @NotEmpty(message = "Nombre no puede ser null o vacío")
    @Size(min = 5, max = 30, message = "El tamaño del nombre del cliente debe estar entre 5 y 30")
    private String nombre;
    
    @NotEmpty(message = "Dirección no puede ser null o vacío")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String direccion;
    
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Telefono debe contener 10 digitos")
    @NotEmpty(message = "Telefono no puede ser null o vacío")
    private String telefono;

    @NotEmpty(message = "Contraseña no puede ser null o vacío")
    private String contrasena;

    private Long clienteId;

}
