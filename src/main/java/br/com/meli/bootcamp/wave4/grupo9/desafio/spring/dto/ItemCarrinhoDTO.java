package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*** DTO para serialização de ItemCarinho
 *
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class ItemCarrinhoDTO {
    /***
     * Quantidade de um mesmo Produto presente no Carrinho/Pedido
     */
    private long quantidade;
    /***
     * Objeto ProdutoDTO contendo os dados do produto inserido no Carrinho/Pedido
     */
    private ProdutoDTO produtoDTO;

    /*** Conversor de lista de ItemCarrinho: de Entidade para DTO
     *
     * @param listaItensCarrinho Lista de Itens no Carrinho/Pedido a ser convertida
     * @return Lista de ItensCarrinhoDTO convertido
     */
    public static List<ItemCarrinhoDTO> converte(List<ItemCarrinho> listaItensCarrinho) {
        List<ItemCarrinhoDTO> listaItemCarrinhoDTO = new ArrayList<>();
        listaItensCarrinho.forEach( prod -> listaItemCarrinhoDTO.add( ItemCarrinhoDTO.converte(prod) ) );
        return listaItemCarrinhoDTO;
    }

    /*** Conversor de ItemCarrinho: de Entidade para DTO
     *
     * @param produto Produto no Carrinho/Pedido a ser convertida
     * @return Lista de ItensCarrinhoDTO convertido
     */
    private static ItemCarrinhoDTO converte (ItemCarrinho produto){
        return new ItemCarrinhoDTO( produto.getQuantidade(), ProdutoDTO.converte( produto.getProduto() ) );
    }
}
