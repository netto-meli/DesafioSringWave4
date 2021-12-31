package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/*** DTO para serialização de Categoria
 *
 * @author Felipe
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

    /*** Conversor da classe Categoria: de DTO para Entidade
     *
     * @param categoriaDTO Objeto Categoria a ser convertido
     * @return Objeto Categoria convertido
     */
    public static Categoria converte(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

    /***
     * {@literal @}Override do método equals
     *
     * @param o Object a ser comparado com a instância desta Classe,
     *          comparando também a ID da Categoria para informar que a Categoria é a mesma.
     * @return Boolean indicando se o Objeto é o mesmo ou não.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaDTO that = (CategoriaDTO) o;
        return id == that.id && Objects.equals(nome, that.nome);
    }

    /***
     * {@literal @}Override do método hash
     *
     * @return Inteiro referente ao retorno do metodo Objects.{@link java.util.Objects hash}(id, nome);
     * @see java.util.Objects hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}