package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception;

/***
 * @author Leo
 */
public class RepositoryException extends RuntimeException {
    private static final long serialVersionUID = -7012775922197850149L;

    public RepositoryException(String mensagem) {
        super(mensagem);
    }

}
