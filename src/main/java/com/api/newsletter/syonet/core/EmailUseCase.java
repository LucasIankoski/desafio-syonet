package com.api.newsletter.syonet.core;

import com.api.newsletter.syonet.entities.Cliente;
import com.api.newsletter.syonet.entities.Noticia;
import jakarta.mail.MessagingException;

import java.util.List;

public interface EmailUseCase {

    void enviaEmail(Cliente cliente, List<Noticia> noticias) throws MessagingException;

    StringBuilder montaMensagemEmail(Cliente cliente, List<Noticia> noticias);
}
