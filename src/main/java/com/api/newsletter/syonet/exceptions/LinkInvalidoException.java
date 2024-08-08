package com.api.newsletter.syonet.exceptions;

public class LinkInvalidoException extends RuntimeException{
    public LinkInvalidoException(){
        super("O endereço do link não é válido");
    }
}
