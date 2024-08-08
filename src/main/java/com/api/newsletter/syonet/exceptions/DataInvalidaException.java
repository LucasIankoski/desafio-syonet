package com.api.newsletter.syonet.exceptions;

public class DataInvalidaException extends RuntimeException{
    public DataInvalidaException(){
        super("Data inválida.");
    }
}
