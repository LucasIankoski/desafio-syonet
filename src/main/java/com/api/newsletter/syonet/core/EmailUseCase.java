package com.api.newsletter.syonet.core;

import com.api.newsletter.syonet.entities.Cliente;
import com.api.newsletter.syonet.entities.Noticia;

import java.util.List;

public interface EmailUseCase {

    void enviaEmail(Cliente cliente, List<Noticia> noticias);

}
