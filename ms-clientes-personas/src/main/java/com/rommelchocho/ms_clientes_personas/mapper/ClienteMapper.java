package com.rommelchocho.ms_clientes_personas.mapper;

import com.rommelchocho.ms_clientes_personas.dto.ClienteDto;
import com.rommelchocho.ms_clientes_personas.entity.Cliente;

public class ClienteMapper {

    public static ClienteDto mapToClienteDto(Cliente cliente, ClienteDto clienteDto) {
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setEstado(cliente.getEstado());
        clienteDto.setTelefono(cliente.getTelefono());
        clienteDto.setDireccion(cliente.getDireccion());
        clienteDto.setClienteId(cliente.getClienteId());
        return clienteDto;
    }

    public static Cliente mapToCliente(ClienteDto clienteDto, Cliente cliente) {
        cliente.setNombre(clienteDto.getNombre());
        cliente.setEstado(clienteDto.getEstado());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setContrasena(clienteDto.getContrasena());
        return cliente;
    }
}
