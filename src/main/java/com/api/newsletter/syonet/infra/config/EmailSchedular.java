package com.api.newsletter.syonet.infra.config;

import com.api.newsletter.syonet.application.ClienteService;
import com.api.newsletter.syonet.application.EmailService;
import com.api.newsletter.syonet.application.NoticiaService;
import com.api.newsletter.syonet.entities.Cliente;
import com.api.newsletter.syonet.entities.Noticia;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailSchedular {

    private final ClienteService clienteService;
    private final NoticiaService noticiaService;
    private final EmailService emailService;

    @Autowired
    public EmailSchedular(ClienteService clienteService, NoticiaService noticiaService, EmailService emailService) {
        this.clienteService = clienteService;
        this.noticiaService = noticiaService;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void enviarNoticias() throws MessagingException {
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
