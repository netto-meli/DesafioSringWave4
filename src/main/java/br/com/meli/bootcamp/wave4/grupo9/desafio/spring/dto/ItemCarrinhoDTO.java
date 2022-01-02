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

    private Long quantidade;

    private ProdutoDTO produtoDTO;

    public static List<ItemCarrinhoDTO> converte(List<ItemCarrinho> listaItensCarrinho) {
        List<ItemCarrinhoDTO> listaItemCarrinhoDTO = new ArrayList<>();
        listaItensCarrinho.forEach( prod -> listaItemCarrinhoDTO.add( ItemCarrinhoDTO.converte(prod) ) );
        return listaItemCarrinhoDTO;
    }

    private static ItemCarrinhoDTO converte (ItemCarrinho produto){
        return new ItemCarrinhoDTO( produto.getQuantidade(), ProdutoDTO.converte( produto.getProduto() ) );
    }
}
