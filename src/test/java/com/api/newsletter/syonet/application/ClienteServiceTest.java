package com.api.newsletter.syonet.application;

import com.api.newsletter.syonet.dtos.ClienteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class ClienteServiceTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteServiceTest clienteServiceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve travar o cadastro de cliente por nome não informado")
    public void deveTravarCadastradoDeClientePorNomeNaoInformado() {

        ClienteDTO cliente = new ClienteDTO("", "lucas.iankoski@outlook.com", Optional.of("1997-06-22"));

        clienteService.validarClienteDTO(cliente);
    }


    @Test
    @DisplayName("Deve travar o cadastro de cliente por e-mail inválido")
    public void deveTravarCadastradoDeClientePorEmailInvalido() {

        ClienteDTO cliente = new ClienteDTO("Lucas", "lucas.iankoski@outlookcom", Optional.of("1997-06-22"));

        clienteService.validarClienteDTO(cliente);
    }

    @Test
    @DisplayName("Deve travar o cadastro de cliente por data com dia inválido")
    public void deveTravarCadastradoDeClientePorDataComDiaInvalido() {

        ClienteDTO cliente = new ClienteDTO("Lucas", "lucas.iankoski@outlook.com", Optional.of("1997-06-40"));

        clienteService.isValidaData(String.valueOf(cliente.dtNascimento()));
        clienteService.validarClienteDTO(cliente);
    }

    @Test
    @DisplayName("Deve travar o cadastro de cliente por data com  mês inválido")
    public void deveTravarCadastradoDeClientePorDataComMesInvalido() {

        ClienteDTO cliente = new ClienteDTO("Lucas", "lucas.iankoski@outlook.com", Optional.of("1997-23-10"));

        clienteService.isValidaData(String.valueOf(cliente.dtNascimento()));
        clienteService.validarClienteDTO(cliente);
    }

    @Test
    @DisplayName("Deve travar o cadastro de cliente por data com  formato inválido")
    public void deveTravarCadastradoDeClientePorDataComFormatoInvalido() {

        ClienteDTO cliente = new ClienteDTO("Lucas", "lucas.iankoski@outlook.com", Optional.of("22/06/1997"));

        clienteService.isValidaData(String.valueOf(cliente.dtNascimento()));
        clienteService.validarClienteDTO(cliente);
    }

    @Test
    @DisplayName("Deve validar o cadastro de cliente")
    public void deveValidarCadastradoDeCliente() {

        ClienteDTO cliente = new ClienteDTO("Lucas", "lucas.iankoski@outlook.com", Optional.of("1997-06-22"));

        clienteService.isValidaData(String.valueOf(cliente.dtNascimento()));
        clienteService.validarClienteDTO(cliente);
    }
}
