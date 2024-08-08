package com.api.newsletter.syonet.core;

import com.api.newsletter.syonet.dtos.NoticiaDTO;
import com.api.newsletter.syonet.entities.Noticia;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public interface NoticiaUseCase {
    void cadastrarNoticia(Noticia noticia);

    void processarNoticia(List<Noticia> noticias);

    List<Noticia> filtrarNaoProcessadas();

    boolean isValidaLink(String link) throws MalformedURLException, URISyntaxException;

    boolean isValidaNoticia(NoticiaDTO noticia) throws Exception;
}
