package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
public class ItemCarrinho extends Produto{
    @Getter
    @Setter
    private long quantidade;

    public BigDecimal calculaValorTotal(){
        return this.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

    public void retiraQuantidadeProduto(long qtdProduto) {
        quantidade -= qtdProduto;
    }
}
