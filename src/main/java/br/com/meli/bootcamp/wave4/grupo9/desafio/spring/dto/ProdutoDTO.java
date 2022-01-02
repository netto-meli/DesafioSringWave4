package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*** DTO para serialização de Produto
 *
 * @author Felipe
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class ProdutoDTO {
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
    private CategoriaDTO categoriaDTO;
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

    /*** Conversor da classe Produto: de Entidade para DTO
     *
     * @param produto Objeto Produto a ser convertido
     * @return Objeto ProdutoDTO convertido
     */
    public static ProdutoDTO converte(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                CategoriaDTO.converte(produto.getCategoria()),
                produto.getMarca(),
                produto.getValor(),
                produto.isFreteGratis(),
                produto.getEstrelas(),
                produto.getQuantidadeEstoque());
    }

    /*** Conversor da classe Produto: de DTO para Entidade
     *
     * @param produtoDTO Objeto Produto a ser convertido
     * @return Objeto Produto convertido
     */
    public static Produto converte(ProdutoDTO produtoDTO) {
        return new Produto(
                produtoDTO.getId(),
                produtoDTO.getNome(),
                CategoriaDTO.converte(produtoDTO.getCategoriaDTO()),
                produtoDTO.getMarca(),
                produtoDTO.getValor(),
                produtoDTO.isFreteGratis(),
                produtoDTO.getEstrelas(),
                produtoDTO.getQuantidadeEstoque() );
    }

    /*** Conversor de lista de Produto: de Entidade para DTO
     *
     * @param produtos Lista de Produto a serem convertidos
     * @return Lista de ProdutosDTO convertidos
     */
    public static List<ProdutoDTO> converte(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDTO::converte).collect(Collectors.toList());
    }

    /* Conversor de lista de Produto: de DTO para Entidade
     *
     * @param produtosDTO Lista de Produto a serem convertidos
     * @return Lista de Produtos convertidos
     */
    public static List<Produto> converteDTO(List<ProdutoDTO> produtosDTO) {
        return produtosDTO.stream().map(ProdutoDTO::converte).collect(Collectors.toList());
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
        ProdutoDTO produtoDTO = (ProdutoDTO) o;
        return id.equals(produtoDTO.id);
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
