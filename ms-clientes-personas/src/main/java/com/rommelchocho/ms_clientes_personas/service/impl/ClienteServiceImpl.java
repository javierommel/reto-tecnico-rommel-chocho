package com.rommelchocho.ms_clientes_personas.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rommelchocho.ms_clientes_personas.dto.ClienteDto;
import com.rommelchocho.ms_clientes_personas.entity.Cliente;
import com.rommelchocho.ms_clientes_personas.exception.ClientAlreadyExistsException;
import com.rommelchocho.ms_clientes_personas.exception.ResourceNotFoundException;
import com.rommelchocho.ms_clientes_personas.mapper.ClienteMapper;
import com.rommelchocho.ms_clientes_personas.messaging.ClienteSyncProducer;
import com.rommelchocho.ms_clientes_personas.repository.ClienteRepository;
import com.rommelchocho.ms_clientes_personas.service.ClienteService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    private ClienteSyncProducer clienteSyncProducer;
    private PasswordEncoder passwordEncoder;

    @Override
    public void createCliente(ClienteDto clienteDto) {
        Cliente cliente = ClienteMapper.mapToCliente(clienteDto, new Cliente());
        Optional<Cliente> optionalCliente = clienteRepository.findByTelefono(clienteDto.getTelefono());
        if (optionalCliente.isPresent()) {
            throw new ClientAlreadyExistsException("Cliente con telefono proporcionado ya existe "
                    + clienteDto.getTelefono());
        }
        cliente.setContrasena(passwordEncoder.encode(clienteDto.getContrasena()));
        Cliente clienteOptional = clienteRepository.save(cliente);
        clienteSyncProducer.enviarActualizacionCliente(clienteOptional.getClienteId(), clienteOptional.getNombre());
    }

    @Override
    public List<ClienteDto> getAllClientes() {
        List<Cliente> cliente = (List<Cliente>) clienteRepository.findAll();
        return cliente.stream().map(c -> ClienteMapper.mapToClienteDto(c, new ClienteDto()))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDto getClienteByTelefono(String telefono) {
        Cliente cliente = clienteRepository.findByTelefono(telefono).orElseThrow(
                () -> new ResourceNotFoundException("Cliente", "telefono", telefono));
        ClienteDto clienteDto = ClienteMapper.mapToClienteDto(cliente, new ClienteDto());
        return clienteDto;
    }

    @Override
    @Transactional
    public boolean updateCliente(ClienteDto clienteDto) {
        boolean isUpdated = false;
        Cliente cliente = clienteRepository.findByTelefono(clienteDto.getTelefono()).orElseThrow(
                () -> new ResourceNotFoundException("Cliente", "telefono", clienteDto.getTelefono().toString()));
        ClienteMapper.mapToCliente(clienteDto, cliente);
        cliente.setContrasena(passwordEncoder.encode(clienteDto.getContrasena()));
        cliente = clienteRepository.save(cliente);
        clienteSyncProducer.enviarActualizacionCliente(cliente.getClienteId(),
                cliente.getNombre());
        return isUpdated;
    }

    @Override
    public boolean deleteCliente(String telefono) {
        Cliente clienteAsync = clienteRepository.findByTelefono(telefono).orElseThrow(
                () -> new ResourceNotFoundException("Cliente", "telefono", telefono));
        clienteRepository.deleteById(clienteAsync.getClienteId());
        clienteSyncProducer.enviarEliminacionCliente(clienteAsync.getClienteId());
        return true;
    }

    @Override
    public Boolean existeCliente(Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findByClienteId(clienteId);
        if (cliente.isPresent())
            return true;
        return false;
    }

}
