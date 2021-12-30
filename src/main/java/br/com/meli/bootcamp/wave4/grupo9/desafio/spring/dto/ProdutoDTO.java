package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Marca;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service.ProdutoService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class ProdutoDTO {
    private final long id;
    private String nome;
    private CategoriaDTO categoriaDTO;
    private String marcaDTO;
    private BigDecimal preco;
    private boolean freteGratis;
    private int estrelas;
    private long quantidadeEstoque;

    Categoria categoria;

    {
        new CategoriaDTO(categoria.getId(), categoria.getNome());
    }


    public static Produto converte(ProdutoDTO dto) {
        Produto produto = Produto.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .categoria(dto.getCategoria())
                .marca(dto.getMarcaDTO())
                .preco(dto.getPreco())
                .estrelas(dto.getEstrelas())
                .quantidadeEstoque(dto.getQuantidadeEstoque())
                .build();

        return produto;
    }

    public static ProdutoDTO converte(Produto produto) {
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .categoria(produto.getCategoria())
                .marcaDTO(produto.getMarca())
                .preco(produto.getPreco())
                .estrelas(produto.getEstrelas())
                .quantidadeEstoque(produto.getQuantidadeEstoque()).
                build();
    }

    public static List<ProdutoDTO> converte(List<Produto> produtoList){
        return produtoList.stream().map(ProdutoDTO::converte).collect(Collectors.toList());
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
