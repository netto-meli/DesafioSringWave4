package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ItemCarrinho {
    private Produto produto;
    private long quantidade;

    public BigDecimal calculaValorTotal(){
        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }
}
