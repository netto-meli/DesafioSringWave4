package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception;

public class NotFoundExceptionProduct extends ClassNotFoundException{
    private static final long serialVersionUID = -3574111920821625288L;

    public NotFoundExceptionProduct(String mensagem) {
        super(mensagem);
    }
}
