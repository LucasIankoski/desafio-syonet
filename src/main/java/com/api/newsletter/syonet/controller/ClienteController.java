package com.api.newsletter.syonet.controller;

import com.api.newsletter.syonet.application.ClienteService;
import com.api.newsletter.syonet.dtos.ClienteDTO;
import com.api.newsletter.syonet.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.validarClienteDTO(clienteDTO);

            LocalDate dataNascimento = clienteService.parseDataNascimento(clienteDTO.dtNascimento().orElse(null));

            Cliente cliente = Cliente.builder()
                    .nome(clienteDTO.nome())
                    .email(clienteDTO.email())
                    .dtNascimento(dataNascimento)
                    .build();

            clienteService.salvar(cliente);
            return new ResponseEntity<>("Cliente criado com sucesso", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity listarClientes() {
        List<Cliente> clientes = clienteService.findAll();

        return ResponseEntity.ok().body(clientes);

    }

}
