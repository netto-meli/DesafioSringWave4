package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import lombok.*;
import org.springframework.stereotype.Service;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
/*** DTO par serialização de Categoria
 *
 * @author Felipe
 * @author Fernando Netto
 */
@Getter
@AllArgsConstructor
public class CategoriaDTO {
    private Long id;
    private String nome;

    public static CategoriaDTO converte(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome());
    }

    public static Categoria converte(CategoriaDTO categoriaDTO) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaDTO that = (CategoriaDTO) o;
        return id == that.id && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}