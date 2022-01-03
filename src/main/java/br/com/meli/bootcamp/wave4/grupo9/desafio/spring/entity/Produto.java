package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.RepositoryException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

/*** Entidade para persistência de Produto
 *
 * @author Felipe
 * @author Fernando Netto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    /***
     * ID do ProdutoDTO no tipo Long
     */
    private Long id;
    /***
     * Nome do ProdutoDTO no formato String
     */
    private String nome;
    /***
     * Objeto CategoriaDTO com informações da categoria do projeto
     */
    private Categoria categoria;
    /***
     * Marca do produto no formato String
     */
    private String marca;
    /***
     * Valor do Produto no formato BigDecimal
     */
    private BigDecimal valor;
    /***
     * Informação se o Frete é grátis ou não
     */
    private boolean freteGratis;
    /***
     * Reputação — Número de estrelas do produto
     */
    private int estrelas;
    /***
     * Quantidade atual deste produto em estoque
     */
    private long quantidadeEstoque;

    /*** Realiza baixa no estoque da quantidade de itens de um produto que foi vendido
     *
     * @param qtd Quantidade de itens vendidos de um produto.
     * @throws RepositoryException Exceção que a aplicação lança, no caso do cliente comprar
     * mais itens do que existentes em estoque, de um mesmo produto
     */
    public void baixarEstoque(long qtd) throws RepositoryException {
        if ( qtd > quantidadeEstoque ){
            String erro = "Imposssível realizar compra, pois o Produto "
                    + this.nome
                    + " tem somente "
                    + this.quantidadeEstoque
                    + " itens em estoque, e você está tentando comprar "
                    + qtd
                    + " itens.";
            throw new RepositoryException(erro);
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
        return id.equals(produto.id);
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
