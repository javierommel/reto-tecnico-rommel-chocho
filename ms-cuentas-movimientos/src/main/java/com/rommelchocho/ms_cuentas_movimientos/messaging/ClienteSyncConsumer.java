package com.rommelchocho.ms_cuentas_movimientos.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rommelchocho.ms_cuentas_movimientos.dto.ClienteSyncMessage;
import com.rommelchocho.ms_cuentas_movimientos.entity.Cliente;
import com.rommelchocho.ms_cuentas_movimientos.repository.ClienteRepository;

@Component
public class ClienteSyncConsumer {

    private final ClienteRepository clienteReplicaRepository;

    public ClienteSyncConsumer(ClienteRepository clienteReplicaRepository) {
        this.clienteReplicaRepository = clienteReplicaRepository;
    }

    @RabbitListener(queues = "clientes.sync.queue")
    public void procesarActualizacionCliente(ClienteSyncMessage mensaje) {
        Long clienteId = mensaje.getClienteId();
        String nombre = mensaje.getNombre();

        Cliente cliente = clienteReplicaRepository.findByClienteId(clienteId)
                .orElse(new Cliente());

        cliente.setClienteId(clienteId);
        cliente.setNombre(nombre);
        clienteReplicaRepository.save(cliente);

        System.out.println("Cliente sincronizado: " + clienteId);
    }

    @RabbitListener(queues = "clientes.delete.queue")
    public void procesarEliminacionCliente(ClienteSyncMessage mensaje) {
        Long clienteId = mensaje.getClienteId();

        clienteReplicaRepository.deleteById(clienteId);
        System.out.println("Cliente eliminado de réplica: " + clienteId);
    }
}