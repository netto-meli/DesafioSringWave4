package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception;

/***
 * @author Leo
 */
public class NotFoundExceptionProduct extends ClassNotFoundException{
    /***
     * serialVersionUID gerado pelo IntelliJ
     */
    private static final long serialVersionUID = -3574111920821625288L;

    /***
     * Construtor da classe
     *
     * @param msg Mensagem sobre a exceção recebida
     */
    public NotFoundExceptionProduct(String msg) {
        super(msg);
    }
}
