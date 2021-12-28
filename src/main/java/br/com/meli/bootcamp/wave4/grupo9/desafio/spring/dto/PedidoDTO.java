package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class PedidoDTO {
    private final long id;
    private final long idCliente;
    private List<ItemCarrinhoDTO> listaItensCarrinho;
    private BigDecimal valorTotal;
}
