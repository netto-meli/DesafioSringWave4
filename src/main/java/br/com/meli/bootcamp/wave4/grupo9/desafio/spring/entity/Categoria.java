package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/*** Entidade para persistência de Categoria
 *
 * @author Felipe
 * @author Fernando Netto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    /***
     * ID da Categoria do tipo Long
     */
    private Long id;
    /***
     * Nome da Categoria do tipo String
     */
    private String nome;

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
        Categoria categoria = (Categoria) o;
        return ( id.equals(categoria.id) || nome.equals(categoria.nome) );
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