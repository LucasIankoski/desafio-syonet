package com.api.newsletter.syonet.application;

import com.api.newsletter.syonet.core.EmailUseCase;
import com.api.newsletter.syonet.entities.Cliente;
import com.api.newsletter.syonet.entities.Noticia;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmailService implements EmailUseCase {

    private final JavaMailSender jmSender;
    private final ClienteService clienteService;

    public EmailService(JavaMailSender jmSender, ClienteService clienteService) {
        this.jmSender = jmSender;
        this.clienteService = clienteService;
    }

    @Override
    public void enviaEmail(Cliente cliente, List<Noticia> noticias) {
        SimpleMailMessage smMessage = new SimpleMailMessage();

        smMessage.setTo(cliente.getEmail());
        smMessage.setSubject("Notícias do dia!");
        smMessage.setText(montaMensagemEmail(cliente, noticias).toString());

        jmSender.send(smMessage);
    }

    @Override
    public StringBuilder montaMensagemEmail(Cliente cliente, List<Noticia> noticias) {
        StringBuilder descEmail = new StringBuilder();
        descEmail.append("Bom dia, ").append(cliente.getNome()).append("!\n\n");

        if(cliente.getDtNascimento() != null){
            if (clienteService.isValidaAniversario(cliente.getDtNascimento())) {
                descEmail.append("Hoje é um dia muito especial. A newsletter Syonet deseja um feliz aniversário!\n\n");
            }
        }

        for (Noticia noticia : noticias) {
            if (noticia.getLink() != null) {
                descEmail.append(noticia.getTitulo()).append(" \n\n").append(noticia.getDescricao()).append(" (").append(noticia.getLink()).append(")\n\n");
            } else {
                descEmail.append(noticia.getTitulo()).append(" \n").append(noticia.getDescricao()).append("\n\n");
            }
        }
        return descEmail;
    }
}
