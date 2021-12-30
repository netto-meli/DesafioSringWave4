package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
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
    private final long idCliente;
    private List<ItemCarrinhoDTO> listaItensCarrinho;
    private BigDecimal valorTotal;

    /*** Conversor da classe Pedido: de Entidade para DTO
     *
     * @param pedido Objeto Pedido a ser convertido
     * @return Objeto PedidoDTO convertido
     */
    public static PedidoDTO converte(Pedido pedido) {
        return new PedidoDTO(
                pedido.getId(),
                pedido.getIdCliente(),
                ItemCarrinhoDTO.converte(pedido.getListaItensCarrinho()),
                pedido.getValorTotal() );
    }
}
