package com.rommelchocho.ms_clientes_personas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rommelchocho.ms_clientes_personas.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByClienteId(Long clienteId);

    Optional<Cliente> findByTelefono(String telefono);
}
