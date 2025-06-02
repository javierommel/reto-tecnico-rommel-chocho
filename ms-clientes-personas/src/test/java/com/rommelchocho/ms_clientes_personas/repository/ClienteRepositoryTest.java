package com.rommelchocho.ms_clientes_personas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.rommelchocho.ms_clientes_personas.entity.Cliente;

@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {
@Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testCrearCliente() {
        
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setDireccion("Calle 123");
        cliente.setTelefono("0996875412");
        cliente.setContrasena("1234");
        cliente.setEstado(true);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        assertNotNull(clienteGuardado.getClienteId());
        assertEquals("0996875412", clienteGuardado.getTelefono());

    }
}
