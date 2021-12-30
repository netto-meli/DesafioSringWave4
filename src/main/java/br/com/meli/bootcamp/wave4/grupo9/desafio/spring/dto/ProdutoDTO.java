package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Marca;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor

public class ProdutoDTO {
    private Long id;
    private String nome;
    private String categoriaDTO;
    private String marca;
    private BigDecimal valor;
    private boolean freteGratis;
    private int estrelas;
    private long quantidadeEstoque;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.categoriaDTO = produto.getCategoria();
        this.marca = produto.getMarca();
        this.valor = produto.getValor();
        this.freteGratis = produto.isFreteGratis();
        this.estrelas = produto.getEstrelas();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
    }



    /*** Conversor da classe Produto: de Entidade para DTO
     *
     * @param produto Objeto Produto a ser convertido
     * @return Objeto ProdutoDTO convertido
     */
    public static ProdutoDTO converte(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getMarca(),
                produto.getValor(),
                produto.isFreteGratis(),
                produto.getEstrelas(),
                produto.getQuantidadeEstoque());
    }

    public static Produto converteDTO(ProdutoDTO produtodto) {
        return new Produto(
                produtodto.getId(),
                produtodto.getNome(),
                produtodto.getCategoriaDTO(),
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
