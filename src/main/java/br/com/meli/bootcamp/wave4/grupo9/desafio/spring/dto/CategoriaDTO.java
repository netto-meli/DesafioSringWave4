package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*** DTO par serialização de Categoria
 *
 * @author
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
}