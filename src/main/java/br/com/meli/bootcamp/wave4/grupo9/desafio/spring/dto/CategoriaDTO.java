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
    /***
     * ID da Categoria do tipo long
     */
    private long id;
    /***
     * Nome da Categoria do tipo String
     */
    private String nome;

    /*** Conversor da classe Categoria: de Entidade para DTO
     *
     * @param categoria Objeto Categoria a ser convertido
     * @return Objeto CategoriaDTO convertido
     */
    public static CategoriaDTO converte(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome());
    }
}