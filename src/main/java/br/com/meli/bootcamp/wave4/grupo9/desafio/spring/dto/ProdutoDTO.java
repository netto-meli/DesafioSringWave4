package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Marca;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProdutoDTO {
    private Long id = Long.getLong("0");
    private String nome = "vazio";

    private CategoriaDTO categoriaDTO;

    private MarcaDTO marcaDTO;
    @JsonIgnore
    private BigDecimal preco;
    @JsonIgnore
    private boolean freteGratis;
    @JsonIgnore
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

    public static ProdutoDTO converteProdutoparaProdDto(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                converteCategoriaParaCategoriaDto(produto.getCategoria()),
                converteMarcaParaMarcaDto(produto.getMarca()),
                produto.getPreco(),
                produto.isFreteGratis(),
                produto.getEstrelas(),
                produto.getQuantidadeEstoque()
                );
    }

    public static Produto converteProdutoDtoParaProduto(ProdutoDTO produtodto) {
        try {
            return new Produto(
                    produtodto.getId(),
                    produtodto.getNome(),
                    converteCategoriaDtoParaCategoria(produtodto.getCategoriaDTO()),
                    converteMarcaDtoParaMarca(produtodto.getMarcaDTO()),
                    produtodto.getPreco(),
                    produtodto.isFreteGratis(),
                    produtodto.getEstrelas(),
                    produtodto.getQuantidadeEstoque()
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ProdutoDTO> converterListaProdParaListaDTO(List<Produto> produtos) {
        return produtos.stream().map(u -> converteProdutoparaProdDto(u)).collect(Collectors.toList());
    }

    public static List<Produto> converterListaProdDTOParaListaProduto(List<ProdutoDTO> produtosDTO) {
        List<Produto> produto = new ArrayList<>();
        produtosDTO.forEach(prod -> {
            Produto p = ProdutoDTO.converteProdutoDtoParaProduto(prod);
            produto.add(p);
        });
        return produto;
    }

    static CategoriaDTO converteCategoriaParaCategoriaDto(Categoria categoria){
        return new CategoriaDTO(categoria.getId(), categoria.getNome());
    }
    static MarcaDTO converteMarcaParaMarcaDto(Marca marca){
        return new MarcaDTO(marca.getId(), marca.getNome());
    }

    static Categoria converteCategoriaDtoParaCategoria(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }
    static Marca converteMarcaDtoParaMarca(MarcaDTO marcaDTO){
        return new Marca(marcaDTO.getId(), marcaDTO.getNome());
    }
}
