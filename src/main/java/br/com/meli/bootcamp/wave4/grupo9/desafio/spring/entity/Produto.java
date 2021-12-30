package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Produto {
    private final long id;
    private String nome;
    private Categoria categoria;
    private String marca;
    private BigDecimal valor;
    private boolean freteGratis;
    private int estrelas;
    private long quantidadeEstoque;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
