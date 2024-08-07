package com.api.newsletter.syonet.core;

import com.api.newsletter.syonet.entities.Cliente;

import java.time.LocalDate;
import java.util.List;

public interface ClienteUseCase {

    Cliente salvar(Cliente cliente);

    List<Cliente> findAll();

    boolean isValidaData(String data);

    boolean isValidaEmail(String email);
}
