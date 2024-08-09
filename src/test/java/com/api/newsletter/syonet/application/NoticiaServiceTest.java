package com.api.newsletter.syonet.application;

import com.api.newsletter.syonet.dtos.NoticiaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class NoticiaServiceTest {


    @Mock
    private NoticiaService noticiaServiceService;

    @InjectMocks
    private NoticiaServiceTest noticiaServiceServiceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve travar o cadastro de notícia por título não informado")
    public void deveTravarCadastradoDeNoticiaPorTituloNaoInformado() throws Exception {

        NoticiaDTO noticia = new NoticiaDTO("", "Isso é uma notícia teste e você será muito bem informado", "");

        noticiaServiceService.isValidaNoticia(noticia);
    }


    @Test
    @DisplayName("Deve travar o cadastro de notícia por descrição não informada")
    public void deveTravarCadastradoDeNoticiaPorDescricaoNaoInformada() throws Exception {

        NoticiaDTO noticia = new NoticiaDTO("Notícia de teste", "", "");

        noticiaServiceService.isValidaNoticia(noticia);
    }

    @Test
    @DisplayName("Deve travar o cadastro de notícia por link inválido")
    public void deveTravarCadastradoDeNoticiaPorLinkInvalido() throws Exception {

        NoticiaDTO noticia = new NoticiaDTO("Notícia de teste", "Isso é uma notícia teste e você será muito bem informado", "https:ww.github.com/");

        noticiaServiceService.isValidaNoticia(noticia);
    }

    @Test
    @DisplayName("Deve validar o cadastro de notícia")
    public void deveValidarCadastradoDeNoticia() throws Exception {

        NoticiaDTO noticia = new NoticiaDTO("Notícia de teste", "Isso é uma notícia teste e você será muito bem informado", "https://github.com/");

        noticiaServiceService.isValidaNoticia(noticia);
    }
}
