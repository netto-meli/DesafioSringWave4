package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*** DTO par serialização de Produto
 *
 * @author Felipe
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class ProdutoDTO {
    private final long id;
    private String nome;
    private CategoriaDTO categoriaDTO;
    private String marca;
    private BigDecimal valor;
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
                CategoriaDTO.converte(produto.getCategoria()),
                produto.getMarca(),
                produto.getPreco(),
                produto.isFreteGratis(),
                produto.getEstrelas(),
                produto.getQuantidadeEstoque());
    }

    public static Produto converte(ProdutoDTO produtodto) {
        return new Produto(
                produtodto.getId(),
                produtodto.getNome(),
                CategoriaDTO.converte(produtodto.getCategoriaDTO()),
                produtodto.getMarca(),
                produtodto.getValor(),
                produtodto.isFreteGratis(),
                produtodto.getEstrelas(),
                produtodto.getQuantidadeEstoque() );
    }

    public static List<ProdutoDTO> converte(List<Produto> produtos) {
        return produtos.stream().map(u -> converte(u)).collect(Collectors.toList());
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


    /*
    public static List<Produto> converterListaProdDTOParaListaProduto(List<ProdutoDTO> produtosDTO) {
        List<Produto> produto = new ArrayList<>();
        produtosDTO.forEach(prod -> {
            Produto p = ProdutoDTO.converteProdutoDtoParaProduto(prod);
            produto.add(p);
        });
        return produto;
    }
    */
}
