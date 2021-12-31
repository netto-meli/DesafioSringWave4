package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Produto {
    private final long id;
    private String nome;
    private Categoria categoria;
    private String marca;
    private BigDecimal valor;
    private boolean freteGratis;
    private int estrelas;
    private long quantidadeEstoque;

    public void baixarEstoque(long qtd){
        if ( qtd > quantidadeEstoque ){
            // TODO throw qtd acima do estoque
            return ;
        }
        quantidadeEstoque -= qtd;
    }

    /***
     * {@literal @}Override do método equals
     *
     * @param o Object a ser comparado com a instância desta Classe,
     *          comparando também a ID do Produto para informar que o Produto é a mesmo.
     * @return Boolean indicando se o Objeto é o mesmo ou não.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }

    /***
     * {@literal @}Override do método hash
     *
     * @return Inteiro referente ao retorno do metodo Objects.{@link java.util.Objects hash}(id, nome);
     * @see java.util.Objects hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
