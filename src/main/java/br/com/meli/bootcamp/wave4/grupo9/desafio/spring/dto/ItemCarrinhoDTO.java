package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
public class ItemCarrinhoDTO extends ProdutoDTO{
    @Getter
    @Setter
    private long quantidade;

    public static List<ItemCarrinhoDTO> converte(List<ItemCarrinho> listaItensCarrinho) {
        List<ItemCarrinhoDTO> j = new ArrayList<>();
        listaItensCarrinho.forEach( prod -> {
            ItemCarrinhoDTO y = ItemCarrinhoDTO.converte(prod);
            j.add(y);
        });
        return j;
    }

    private static ItemCarrinhoDTO converte (ItemCarrinho i){
        return null;//new ItemCarrinhoDTO( Produto.converte( i.getProduto() ),i.getQuantidade());
    }
}
