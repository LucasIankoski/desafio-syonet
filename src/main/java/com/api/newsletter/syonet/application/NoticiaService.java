package com.api.newsletter.syonet.application;

import com.api.newsletter.syonet.core.NoticiaUseCase;
import com.api.newsletter.syonet.entities.Noticia;
import com.api.newsletter.syonet.repository.NoticiaRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void cadastrarNoticia(Noticia noticia) {
        noticiaRepository.save(noticia);
    }

    @Override
    @Transactional
    public void processarNoticia(List<Noticia> noticias) {
        noticias.forEach(noticia -> noticia.setProcessada(true));
        noticiaRepository.saveAll(noticias);
    }

    @Override
    @Transactional
    public List<Noticia> filtrarNaoProcessadas() {
        return noticiaRepository.findByProcessadaFalse();
    }
}
