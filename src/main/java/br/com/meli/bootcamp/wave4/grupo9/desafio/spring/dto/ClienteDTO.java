package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*** DTO para serialização de Cliente
 *
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class ClienteDTO {
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

    /*** Conversor de Lista Cliente: de Entidade para DTO
     *
     * @param listaCliente Lista de Cliente a ser convertida
     * @return Lista de ClienteDTO convertido
     */
    public static List<ClienteDTO> converte(List<Cliente> listaCliente) {
        List<ClienteDTO> listaCliDTO = new ArrayList<>();
        for (Cliente cli : listaCliente) {
            ClienteDTO cliDTO = ClienteDTO.converte(cli);
            listaCliDTO.add(cliDTO);
        }
        return listaCliDTO;
    }

    /*** Conversor da classe Cliente: de Entidade para DTO
     *
     * @param cli Objeto Cliente a ser convertido
     * @return Objeto ClienteDTO convertido
     */
    private static ClienteDTO converte(Cliente cli) {
        return new ClienteDTO(
                cli.getId(),
                cli.getNome(),
                cli.getEndereco(),
                cli.getEstado(),
                cli.getCpf() );
    }
}
