package com.api.newsletter.syonet.application;

import com.api.newsletter.syonet.core.EmailUseCase;
import com.api.newsletter.syonet.entities.Cliente;
import com.api.newsletter.syonet.entities.Noticia;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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
    public void enviaEmail(Cliente cliente, List<Noticia> noticias) throws MessagingException {
        MimeMessage mMessage = jmSender.createMimeMessage();
        MimeMessageHelper smMessageHelper = new MimeMessageHelper(mMessage, true, "UTF-8");

        smMessageHelper.setTo(cliente.getEmail());
        smMessageHelper.setSubject("Notícias do dia!");
        smMessageHelper.setText(montaMensagemEmail(cliente, noticias).toString(), true);

        jmSender.send(mMessage);
    }

    @Override
    public StringBuilder montaMensagemEmail(Cliente cliente, List<Noticia> noticias) {
        StringBuilder descEmail = new StringBuilder();
        descEmail.append("Bom dia, ").append(cliente.getNome()).append("!<br/><br/>");

        if (cliente.getDtNascimento() != null) {
            if (clienteService.isValidaAniversario(cliente.getDtNascimento())) {
                descEmail.append("Hoje é um dia muito especial. A newsletter Syonet deseja um feliz aniversário!<br/><br/>");
            }
        }

        for (Noticia noticia : noticias) {
            if (noticia.getLink() != null) {
                descEmail.append("<a href=\"")
                        .append(noticia.getLink())
                        .append("\"><span style=\"font-size:20px;\">")
                        .append(noticia.getTitulo())
                        .append("</a><br/><br/>");
            } else {
                descEmail.append("<span style=\"font-size:20px;\">").append(noticia.getTitulo()).append("<br/><br/>");
            }
            descEmail.append(noticia.getDescricao()).append("<br/><br/>");
        }

        return descEmail;
    }
}
