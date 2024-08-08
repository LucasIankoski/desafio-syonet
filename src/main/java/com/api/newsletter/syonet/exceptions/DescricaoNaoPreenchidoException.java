package com.api.newsletter.syonet.exceptions;

public class DescricaoNaoPreenchidoException extends RuntimeException{
    public DescricaoNaoPreenchidoException(){
        super("O preenchimento da descrição é obrigatório");
    }
}
