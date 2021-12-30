package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ItemCarrinho{
    private long quantidade;
    private Produto produto;

    public BigDecimal calculaValorTotalProduto(){
        return produto.getValor().multiply(BigDecimal.valueOf(quantidade));
    }

    public void retiraQuantidadeProduto(long qtdProduto) {
        quantidade -= qtdProduto;
    }
}
