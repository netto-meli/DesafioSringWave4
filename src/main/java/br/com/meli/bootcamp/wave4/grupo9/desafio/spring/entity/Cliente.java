package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/*** Entidade para persistência de Cliente
 *
 * @author Fernando Netto
 * @author Rafael
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    /***
     * ID do Cliente do tipo Long
     */
    private Long id;
    /***
     * Nome do Cliente do tipo String
     */
    private String nome;
    /***
     * Endereço do Cliente do tipo String
     */
    private String endereco;
    /***
     * Estado do Cliente do tipo String
     */
    private String estado;
    /***
     * CPF do Cliente do tipo String
     */
    private String cpf;

    /***
     * {@literal @}Override do método equals
     *
     * @param o Object a ser comparado com a instância desta Classe,
     *          comparando também a ID do Cliente para informar que o Cliente é a mesmo.
     * @return Boolean indicando se o Objeto é o mesmo ou não.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return ( id.equals(cliente.id) || cpf.equals(cliente.getCpf()) );
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
