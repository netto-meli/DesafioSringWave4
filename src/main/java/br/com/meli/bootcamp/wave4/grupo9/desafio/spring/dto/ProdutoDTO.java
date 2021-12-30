package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ProdutoDTO {
    private final long id;
    private String nome;
    private CategoriaDTO categoriaDTO;
    private String marca;
    private BigDecimal preco;
    private boolean freteGratis;
    private int estrelas;
    private long quantidadeEstoque;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoDTO produtoDTO = (ProdutoDTO) o;
        return id == produtoDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
