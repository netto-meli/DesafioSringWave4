package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception;

public class CartManagementException extends RuntimeException{
    private static final long serialVersionUID = -3574111920821625288L;

    public CartManagementException(String msg) {
        super(msg);
    }
}
