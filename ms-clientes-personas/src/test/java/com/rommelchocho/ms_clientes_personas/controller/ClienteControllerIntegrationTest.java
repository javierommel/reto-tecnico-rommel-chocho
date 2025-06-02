package com.rommelchocho.ms_clientes_personas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.rommelchocho.ms_clientes_personas.dto.ClienteDto;
import com.rommelchocho.ms_clientes_personas.dto.ResponseDto;
import com.rommelchocho.ms_clientes_personas.messaging.ClienteSyncProducer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ClienteControllerIntegrationTest {
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Mock 
    private ClienteSyncProducer clienteSyncProducer;

    @TestConfiguration
    static class MockProducerConfig {
        @Bean
        public ClienteSyncProducer clienteSyncProducer() {
            return Mockito.mock(ClienteSyncProducer.class); 
        }
    }

    @SuppressWarnings("null")
    @Test
    public void testCrearCliente() {

        Mockito.doNothing().when(clienteSyncProducer).enviarActualizacionCliente(Mockito.anyLong(),
                Mockito.anyString());

        ClienteDto cliente = new ClienteDto();
        cliente.setNombre("Juan Perez");
        cliente.setDireccion("Calle 123");
        cliente.setTelefono("0993487526");
        cliente.setContrasena("12345");
        cliente.setEstado(true);

        String url = "http://localhost:" + port + "/clientes";

        ResponseEntity<ResponseDto> response = restTemplate.postForEntity(url, cliente, ResponseDto.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("201", response.getBody().getStatusCode());
    }
}
