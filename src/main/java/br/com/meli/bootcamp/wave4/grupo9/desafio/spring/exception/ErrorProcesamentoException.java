package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception;

import java.io.IOException;

/***
 * @author Leo
 */
public class ErrorProcesamentoException extends IOException{
    /***
     * serialVersionUID gerado pelo IntelliJ
     */
    private static final long serialVersionUID = -7012775922197850149L;

    /***
     * Construtor da classe
     *
     * @param msg Mensagem sobre a exceção recebida
     */
    public ErrorProcesamentoException(String msg) {
        super(msg);
    }

}
