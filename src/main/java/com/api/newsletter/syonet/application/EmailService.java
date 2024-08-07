package com.api.newsletter.syonet.application;

import com.api.newsletter.syonet.adapters.EnvioEmailGateway;
import com.api.newsletter.syonet.core.EmailUseCase;
import com.api.newsletter.syonet.entities.Cliente;
import com.api.newsletter.syonet.entities.Noticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmailService implements EmailUseCase {

    private final JavaMailSender jmSender;

    public EmailService(JavaMailSender jmSender) {
        this.jmSender = jmSender;
    }

    @Override
    public void enviaEmail(Cliente cliente, List<Noticia> noticias) {
        SimpleMailMessage smMessage = new SimpleMailMessage();

        smMessage.setTo(cliente.getEmail());
        smMessage.setSubject("Syonet Newsletter");

        StringBuilder descEmail = new StringBuilder();
        descEmail.append("Bom dia, ").append(cliente.getNome()).append("!\n\n");

        if (cliente.getDtNascimento() != null && cliente.getDtNascimento().equals(LocalDate.now())) {
            descEmail.append("Hoje é um dia muito especial. A newsletter Syonet deseja um feliz aniversário!\n\n");
        }

        for (Noticia noticia : noticias) {
            if (noticia.getLink() != null) {
                descEmail.append(noticia.getTitulo()).append(" - ").append(noticia.getDescricao()).append(" (").append(noticia.getLink()).append(")\n\n");
            } else {
                descEmail.append(noticia.getTitulo()).append(" - ").append(noticia.getDescricao()).append("\n\n");
            }
        }

        smMessage.setText(descEmail.toString());
        jmSender.send(smMessage);
    }
}
