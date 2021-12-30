package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/*** DTO par serialização de Cliente
 *
 * @author
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String estado;
    private String cpf;

    @JsonIgnore
    private List<PedidoDTO> listaPedidoDTOS;
}
