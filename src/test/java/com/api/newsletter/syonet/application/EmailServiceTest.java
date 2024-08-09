package com.api.newsletter.syonet.application;

import com.api.newsletter.syonet.entities.Cliente;
import com.api.newsletter.syonet.entities.Noticia;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Mock
    private JavaMailSender jmSender;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testa se o envio de e-mail está sendo efetuado com sucesso")
    void testaEnviaEmail() throws MessagingException {
        Cliente cliente = new Cliente();
        cliente.setEmail("lucas@email.com");
        cliente.setNome("Lucas");

        Noticia noticia = new Noticia();
        noticia.setTitulo("Título");
        noticia.setDescricao("Descrição da Notícia");
        noticia.setLink("https://github.com/");

        MimeMessageHelper mockHelper = mock(MimeMessageHelper.class);
        when(jmSender.createMimeMessage()).thenReturn(mock(MimeMessage.class));

        emailService.enviaEmail(cliente, List.of(noticia));

        verify(jmSender, times(1)).send(any(MimeMessage.class));
    }

    @Test
    void testaMontaMensagemEmailComAniversarioENoticias() {
        Cliente cliente = new Cliente();
        cliente.setNome("Lucas");
        cliente.setEmail("lucas@email.com");
        cliente.setDtNascimento(LocalDate.now());

        when(clienteService.isValidaAniversario(cliente.getDtNascimento())).thenReturn(true);

        Noticia noticiaComLink = new Noticia();
        noticiaComLink.setTitulo("Título da notícia com link");
        noticiaComLink.setDescricao("Descrição da Notícia com link");
        noticiaComLink.setLink("https://github.com/");

        Noticia noticiaSemLink = new Noticia();
        noticiaSemLink.setTitulo("Título da notícia sem Link");
        noticiaSemLink.setDescricao("Descrição da Notícia sem Link");

        List<Noticia> noticias = List.of(noticiaComLink, noticiaSemLink);

        StringBuilder mensagemEmail = emailService.montaMensagemEmail(cliente, noticias);

        assertTrue(mensagemEmail.toString().contains("A newsletter Syonet deseja um feliz aniversário!"),
                "A mensagem de aniversário não foi incluída corretamente.");

        assertTrue(mensagemEmail.toString().contains("<a href=\"https://github.com/\"><span style=\"font-size:20px;\">Título da notícia com link</a><br/><br/>"),
                "A notícia com link não foi formatada corretamente.");

        assertTrue(mensagemEmail.toString().contains("<span style=\"font-size:20px;\">Título da notícia sem Link<br/><br/>"),
                "A notícia sem link não foi formatada corretamente.");
    }

    @Test
    void testaMontaMensagemEmailSemAniversarioENoticias() {
        Cliente cliente = new Cliente();
        cliente.setNome("Lucas");
        cliente.setEmail("lucas@email.com");
        cliente.setDtNascimento(LocalDate.now().minusMonths(1));

        when(clienteService.isValidaAniversario(cliente.getDtNascimento())).thenReturn(false);

        Noticia noticiaComLink = new Noticia();
        noticiaComLink.setTitulo("Título da notícia com link");
        noticiaComLink.setDescricao("Descrição da Notícia com link");
        noticiaComLink.setLink("https://github.com/");

        Noticia noticiaSemLink = new Noticia();
        noticiaSemLink.setTitulo("Título da notícia sem Link");
        noticiaSemLink.setDescricao("Descrição da Notícia sem Link");

        List<Noticia> noticias = List.of(noticiaComLink, noticiaSemLink);

        StringBuilder mensagemEmail = emailService.montaMensagemEmail(cliente, noticias);

        assertTrue(!mensagemEmail.toString().contains("A newsletter Syonet deseja um feliz aniversário!"),
                "A mensagem de aniversário não foi incluída corretamente.");

        assertTrue(mensagemEmail.toString().contains("<a href=\"https://github.com/\"><span style=\"font-size:20px;\">Título da notícia com link</a><br/><br/>"),
                "A notícia com link não foi formatada corretamente.");

        assertTrue(mensagemEmail.toString().contains("<span style=\"font-size:20px;\">Título da notícia sem Link<br/><br/>"),
                "A notícia sem link não foi formatada corretamente.");
    }

}
