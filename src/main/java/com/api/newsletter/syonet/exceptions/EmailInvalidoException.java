package com.api.newsletter.syonet.exceptions;

public class EmailInvalidoException extends RuntimeException{
    public EmailInvalidoException(){
        super("E-mail inválido.");
    }
}
