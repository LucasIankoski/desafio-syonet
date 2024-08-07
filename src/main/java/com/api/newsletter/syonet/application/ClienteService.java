package com.api.newsletter.syonet.application;

import com.api.newsletter.syonet.core.ClienteUseCase;
import com.api.newsletter.syonet.entities.Cliente;
import com.api.newsletter.syonet.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClienteService implements ClienteUseCase {


    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.clienteRepository = repository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {

        return clienteRepository.save(cliente);

    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public boolean isValidaData(String data) {

        if (!isValidaFormatoData(data)) {
            return false;
        }
        try {
            LocalDate parsedDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dataAtual = LocalDate.now();
            return !parsedDate.isAfter(dataAtual);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isValidaFormatoData(String data) {

        String regex = "\\d{4}-\\d{2}-\\d{2}";
        if (!Pattern.matches(regex, data)) {
            return false;
        }


        try {
            LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean isValidaEmail(String email) {
        boolean emailValido = false;

        if (!email.isEmpty()) {
            String expressaoRegular = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(expressaoRegular, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                emailValido = true;
            }

        }

        return emailValido;
    }
}
