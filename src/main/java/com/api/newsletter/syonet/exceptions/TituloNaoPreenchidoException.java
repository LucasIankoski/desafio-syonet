package com.api.newsletter.syonet.exceptions;

public class TituloNaoPreenchidoException extends RuntimeException {
    public TituloNaoPreenchidoException(){
        super("O preenchimento do título é obrigatório");
    }
}
