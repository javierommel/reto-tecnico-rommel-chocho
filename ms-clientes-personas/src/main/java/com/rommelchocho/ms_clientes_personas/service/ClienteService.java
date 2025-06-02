package com.rommelchocho.ms_clientes_personas.service;

import java.util.List;

import com.rommelchocho.ms_clientes_personas.dto.ClienteDto;

public interface ClienteService {
    
    void createCliente(ClienteDto clienteDto);

    List<ClienteDto> getAllClientes();

    ClienteDto getClienteByTelefono(String telefono);

    boolean updateCliente(ClienteDto clienteDto);

    boolean deleteCliente(String telefono);

    Boolean existeCliente(Long clienteId);
}
