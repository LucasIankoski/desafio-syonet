package com.api.newsletter.syonet.controller;

import com.api.newsletter.syonet.application.ClienteService;
import com.api.newsletter.syonet.application.EmailService;
import com.api.newsletter.syonet.application.NoticiaService;
import com.api.newsletter.syonet.entities.Cliente;
import com.api.newsletter.syonet.entities.Noticia;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    private final NoticiaService noticiaService;
    private final ClienteService clienteService;

    public EmailController(EmailService emailService, NoticiaService noticiaService, ClienteService clienteService) {
        this.emailService = emailService;
        this.noticiaService = noticiaService;
        this.clienteService = clienteService;
    }

    @PostMapping
    public void sendEmail() throws MessagingException {
        List<Noticia> noticias = noticiaService.filtrarNaoProcessadas();
        if (noticias.isEmpty()) {
            return;
        }

        List<Cliente> clientes = clienteService.findAll();
        for (Cliente cliente : clientes) {
            emailService.enviaEmail(cliente, noticias);
        }

        noticiaService.processarNoticia(noticias);
    }
}

