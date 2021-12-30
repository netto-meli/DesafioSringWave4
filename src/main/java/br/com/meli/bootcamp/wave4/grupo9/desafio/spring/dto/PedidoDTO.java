package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*** DTO par serialização de Pedido
 *
 * @author
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private Long idClienteDTO;
    private List<ItemCarrinhoDTO> listaItensCarrinhoDTO;
    private BigDecimal valorTotal;

    public static PedidoDTO converte(Pedido pedido) {
        return new PedidoDTO(
                pedido.getId(),
                pedido.getIdCliente(),
                ItemCarrinhoDTO.converte(pedido.getListaItensCarrinho()),
                pedido.getValorTotal());
    }
}
