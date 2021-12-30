package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import com.sun.source.tree.ReturnTree;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Pedido {
    private Long id;
    private Long idCliente;
    private List<ItemCarrinho> listaItensCarrinho;
    private BigDecimal valorTotal;

    public void calculaValorTotalPedido(){
        BigDecimal valorPedido = BigDecimal.ZERO;
        for (ItemCarrinho item : listaItensCarrinho) {
            BigDecimal valorTotalUmProduto = item.calculaValorTotalProduto();
            valorPedido = valorTotalUmProduto.add(valorPedido);
        }
        this.valorTotal = valorPedido;
    }

    public ItemCarrinho getItemCarrinho(Long idProduto) {
        return listaItensCarrinho.stream()
                .filter( ic -> ic.getProduto().getId() == idProduto )
                .findAny()
                .orElse(null);
    }

    public void atualizaCarrinho(ItemCarrinho itemCarrinho) {
        listaItensCarrinho.removeIf(itemCarrinho::equals);
        if (itemCarrinho.getQuantidade() > 0 ) listaItensCarrinho.add(itemCarrinho);
        this.calculaValorTotalPedido();
    }

}
