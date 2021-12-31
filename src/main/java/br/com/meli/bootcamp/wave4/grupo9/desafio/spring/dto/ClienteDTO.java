package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
     * ID do Cliente do tipo long, com modificador de acesso <i>final</i>
     * para que a ID depois de atribuída, não possa ser alterada.
     */
    private long id;
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
     * Lista de PedidosDTO, com a notação <i>{@literal @}JsonIgnore</i>,
     * para que o JSON não utilize esse atributo em seu conteúdo.
     */
    @JsonIgnore
    private List<PedidoDTO> listaPedidoDTOS;

    public static List<ClienteDTO> converte(List<Cliente> listaCliente) {
        List<ClienteDTO> listaCliDTO = new ArrayList<>();
        for (Cliente cli : listaCliente) {
            ClienteDTO cliDTO = ClienteDTO.converte(cli);
            listaCliDTO.add(cliDTO);
        }
        return listaCliDTO;
    }

    private static ClienteDTO converte(Cliente cli) {
        return new ClienteDTO(
                cli.getId(),
                cli.getNome(),
                cli.getEndereco(),
                cli.getEstado(),
                cli.getCpf(),
                PedidoDTO.converte( cli.getListaPedidos()) );
    }
}
