package com.api.newsletter.syonet.exceptions;

public class NomeObrigatorioException extends RuntimeException{
    public NomeObrigatorioException(){
        super("Preenchimento de nome é obrigatório.");
    }
}
