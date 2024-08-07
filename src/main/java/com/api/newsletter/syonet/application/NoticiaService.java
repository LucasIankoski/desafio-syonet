package com.api.newsletter.syonet.application;

import com.api.newsletter.syonet.core.NoticiaUseCase;
import com.api.newsletter.syonet.entities.Noticia;
import com.api.newsletter.syonet.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiaService implements NoticiaUseCase {

    private final NoticiaRepository noticiaRepository;

    @Autowired
    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    @Override
    public void cadastrarNoticia(Noticia noticia) {
        noticiaRepository.save(noticia);
    }

    @Override
    public void processarNoticia(List<Noticia> noticias) {
        noticias.forEach(noticia -> noticia.setProcessada(true));
        noticiaRepository.saveAll(noticias);
    }

    @Override
    public List<Noticia> filtrarNaoProcessadas() {
        return noticiaRepository.findByNaoProcessadas();
    }
}
