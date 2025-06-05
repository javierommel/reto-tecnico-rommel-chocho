package com.rommelchocho.ms_clientes_personas.service;

import java.util.List;

import com.rommelchocho.ms_clientes_personas.dto.ClienteDto;

public interface ClienteService {
    
    void createCliente(ClienteDto clienteDto);

    List<ClienteDto> getAllClientes();

    ClienteDto getClienteByTelefono(String telefono);

    boolean updateCliente(Long clienteId, ClienteDto clienteDto);

    boolean deleteCliente(Long clienteId);

    Boolean existeCliente(Long clienteId);
}
