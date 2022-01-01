package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception;

import java.io.IOException;

public class ErrorProcesamentoException extends IOException{
    private static final long serialVersionUID = -7012775922197850149L;
    public ErrorProcesamentoException(String mensagem) {
        super(mensagem);
    }

}
