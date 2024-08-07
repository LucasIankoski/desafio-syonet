package com.api.newsletter.syonet.core;

import com.api.newsletter.syonet.entities.Noticia;

import java.util.List;

public interface NoticiaUseCase {
    void cadastrarNoticia(Noticia noticia);

    void processarNoticia(List<Noticia> noticias);

    List<Noticia> filtrarNaoProcessadas();
}
