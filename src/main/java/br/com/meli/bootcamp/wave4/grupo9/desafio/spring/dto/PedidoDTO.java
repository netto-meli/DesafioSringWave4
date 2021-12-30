package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private final long idCliente;
    private List<ItemCarrinhoDTO> listaItensCarrinho;
    private BigDecimal valorTotal;

    public static PedidoDTO converte(Pedido pedido) {
        List<ItemCarrinhoDTO> listaItensCarrinhoDTO = ItemCarrinhoDTO.converte(pedido.getListaItensCarrinho());
        return new PedidoDTO( pedido.getId(), pedido.getIdCliente(), listaItensCarrinhoDTO, pedido.getValorTotal() );
    }
}
