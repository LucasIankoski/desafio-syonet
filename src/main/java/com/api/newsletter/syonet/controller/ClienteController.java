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

            if(clienteDTO.nome().isEmpty()){
                throw new IllegalArgumentException("Preenchimento de nome é obrigatório.");
            }

            if(!clienteService.isValidaEmail(clienteDTO.email())){
                throw new IllegalArgumentException("E-mail inválido.");
            }

            if(!clienteDTO.dtNascimento().isEmpty()){
                if(!clienteService.isValidaData(clienteDTO.dtNascimento())){
                    throw new IllegalArgumentException("Data inválida.");
                }
            }

            Cliente cliente = Cliente.builder()
                    .nome(clienteDTO.nome())
                    .email(clienteDTO.email())
                    .dtNascimento(LocalDate.parse(clienteDTO.dtNascimento()))
                    .build();

            clienteService.salvar(cliente);
            return new ResponseEntity<>("Cliente criado com sucesso", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity listarClientes() {
        List<Cliente> clientes = clienteService.findAll();

        return ResponseEntity.ok().body(clientes);

    }

}
