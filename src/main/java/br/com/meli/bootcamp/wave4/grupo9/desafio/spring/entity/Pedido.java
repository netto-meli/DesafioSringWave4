package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Pedido {
    private Long id;
    private Long idCliente;
    private List<ItemCarrinho> listaItensCarrinho;
    private BigDecimal valorFrete;
    private BigDecimal valorTotal;

    public void calculaValorTotalPedido(String endereco){
        BigDecimal valorPedido = BigDecimal.ZERO;
        boolean isFreteGratis = true;
        for (ItemCarrinho item : listaItensCarrinho) {
            BigDecimal valorTotalUmProduto = item.calculaValorTotalProduto();
            valorPedido = valorTotalUmProduto.add(valorPedido);
            /*
             Se algum dos produtos no pedido não tiver frete gratis,
             o pedido precisa calcular o valor do frete
             */
            if (!item.getProduto().isFreteGratis()) isFreteGratis = false;
        }
        calculaFrete(isFreteGratis, endereco);
        this.valorTotal = valorPedido.add(this.valorFrete);
    }

    private void calculaFrete(boolean isFreteGratis, String endereco) {
        this.valorFrete = BigDecimal.ZERO;
        if (!isFreteGratis) {
            long temp = 1;
            String mockFrete = endereco.toLowerCase();
            final String alphabet = "abcdefghijklmnopqrstuvwxyz";
            for(int i=0; i < mockFrete.length(); i++){
                temp += (alphabet.indexOf(mockFrete.charAt(i))+1);
            }
            this.valorFrete = BigDecimal.valueOf(temp);
        }
    }

    public ItemCarrinho getItemCarrinho(Long idProduto) {
        return listaItensCarrinho.stream()
                .filter( ic -> ic.getProduto().getId().equals(idProduto))
                .findAny()
                .orElse(null);
    }

    public void atualizaCarrinho(ItemCarrinho itemCarrinho, String endereco) {
        listaItensCarrinho.removeIf(itemCarrinho::equals);
        if (itemCarrinho.getQuantidade() > 0 ) listaItensCarrinho.add(itemCarrinho);
        this.calculaValorTotalPedido(endereco);
    }

}
