package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

/*** DTO par serialização de Produto
 *
 * @author
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String nome;
    private CategoriaDTO categoriaDTO;
    private String marca;
    private BigDecimal preco;
    private boolean freteGratis;
    private int estrelas;
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
                CategoriaDTO.converte( produto.getCategoria() ),
                produto.getMarca(),
                produto.getPreco(),
                produto.isFreteGratis(),
                produto.getEstrelas(),
                produto.getQuantidadeEstoque());
    }

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
