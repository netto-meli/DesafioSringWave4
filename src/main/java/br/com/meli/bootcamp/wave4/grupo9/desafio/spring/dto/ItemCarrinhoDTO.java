package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ItemCarrinhoDTO {
    private ProdutoDTO produtoDTO;
    private long quantidade;
}
