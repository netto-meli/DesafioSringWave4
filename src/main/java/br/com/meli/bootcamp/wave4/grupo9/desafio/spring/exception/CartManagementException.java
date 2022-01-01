package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception;

/*** Exceção no gerenciamento do carrinho
 * @author Fernando Netto
 */
public class CartManagementException extends RuntimeException{
    /***
     * serialVersionUID gerado pelo IntelliJ
     */
    private static final long serialVersionUID = 7108601999472973809L;

    /***
     * Construtor da classe
     *
     * @param msg Mensagem sobre a exceção recebida
     */
    public CartManagementException(String msg) {
        super(msg);
    }
}
