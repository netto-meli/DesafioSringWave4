package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception;

import java.io.IOException;

/***
 * @author Leo
 */
public class RepositoryException extends IOException {
    /***
     * serialVersionUID gerado pelo IntelliJ
     */
    private static final long serialVersionUID = 2988459374810028462L;

    /***
     * Construtor da classe
     *
     * @param msg Mensagem sobre a exceção recebida
     */
    public RepositoryException(String msg) {
        super(msg);
    }

}
