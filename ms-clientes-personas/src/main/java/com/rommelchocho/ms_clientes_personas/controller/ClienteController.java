package com.rommelchocho.ms_clientes_personas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rommelchocho.ms_clientes_personas.dto.ClienteDto;
import com.rommelchocho.ms_clientes_personas.dto.ResponseDto;
import com.rommelchocho.ms_clientes_personas.service.ClienteService;
import constants.ClientConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
@Validated
public class ClienteController {

    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ResponseDto> createCliente(@Valid @RequestBody ClienteDto clienteDto) {
        clienteService.createCliente(clienteDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ClientConstants.STATUS_201, ClientConstants.MESSAGE_201));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @GetMapping("/{telefono}")
    public ResponseEntity<ClienteDto> getClienteById(
            @PathVariable @Pattern(regexp = "(^$|[0-9]{10})", message = "Telefono debe contener 10 dígitos") String telefono) {
        ClienteDto clienteDto = clienteService.getClienteByTelefono(telefono);
        return ResponseEntity.status(HttpStatus.OK).body(clienteDto);
    }

    @PutMapping("/{telefono}")
    public ResponseEntity<?> updateCliente(
            @PathVariable @Pattern(regexp = "(^$|[0-9]{10})", message = "Telefono debe contener 10 dígitos") String telefono,
            @Valid @RequestBody ClienteDto clienteDto) {
        boolean isUpdated = clienteService.updateCliente(clienteDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ClientConstants.STATUS_200, ClientConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ClientConstants.STATUS_417, ClientConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/{telefono}")
    public ResponseEntity<ResponseDto> deleteCliente(
            @PathVariable @Pattern(regexp = "(^$|[0-9]{10})", message = "Telefono debe contener 10 dígitos") String telefono) {
        boolean isDeleted = clienteService.deleteCliente(telefono);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ClientConstants.STATUS_200, ClientConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ClientConstants.STATUS_417, ClientConstants.MESSAGE_417_DELETE));
        }
    }
}
