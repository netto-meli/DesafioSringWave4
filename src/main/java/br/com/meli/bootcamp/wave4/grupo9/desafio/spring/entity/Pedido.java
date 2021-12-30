package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Pedido {
    private Long id;
    private final long idCliente;
    private List<ItemCarrinho> listaItensCarrinho;
    private BigDecimal valorTotal;

    public void calculaValorTotal(){
        BigDecimal valorPedido = BigDecimal.ZERO;
        for (ItemCarrinho item : listaItensCarrinho) {
            BigDecimal valorTotalUmProduto = item.getPreco().multiply( new BigDecimal(item.getQuantidade()) );
            valorPedido = valorTotalUmProduto.add(valorPedido);
        }
        this.valorTotal = valorPedido;
    }

    public ItemCarrinho getItemCarrinho(Long idProduto) {
        return listaItensCarrinho.stream()
                .filter( ic -> ic.getId() == idProduto )
                .findAny()
                .orElse(null);
    }

    public void atualizaCarrinho(ItemCarrinho itemCarrinho) {
        listaItensCarrinho.removeIf(itemCarrinho::equals);
        if (itemCarrinho.getQuantidade() > 0 ) listaItensCarrinho.add(itemCarrinho);
        this.calculaValorTotal();
    }
}
