package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ClienteDTO {
    private final long id;
    private String nome;
    private String endereco;
    private String estado;
    private long cpf;
    private List<PedidoDTO> listaPedidoDTOS;
}
