package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PedidoDTO {
    private long id;
    private ClienteDTO clienteDTO;
    private List<ItemCarrinhoDTO> listaItensCarrinhoDTO;
    private BigDecimal valorTotal;
}
