package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.dto;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*** DTO para serialização de Pedido
 *
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class PedidoDTO {
    /***
     * ID do PedidoDTO no tipo Long
     */
    private Long id;
    /***
     * ID do Cliente no tipo Long
     */
    private Long idCliente;
    /***
     * Lista de ItemCarrinhodTO (produto no pedido) no formato BigDecimal
     */
    private List<ItemCarrinhoDTO> listaItensCarrinho;
    /***
     * Valor do frete do Pedido no formato BigDecimal
     */
    private BigDecimal valorFrete;
    /***
     * Valor total do Pedido no formato BigDecimal
     */
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
                pedido.getValorFrete(),
                pedido.getValorTotal() );
    }

    /*** Conversor de lista de Pedido: de Entidade para DTO
     *
     * @param listaPedidos Objeto Pedido a ser convertido
     * @return Lista de PeddidoDTO convertido
     */
    public static List<PedidoDTO> converte(List<Pedido> listaPedidos) {
        List<PedidoDTO> listaPdDTO = new ArrayList<>();
        for (Pedido pd : listaPedidos) {
            PedidoDTO pdDTO = PedidoDTO.converte(pd);
            listaPdDTO.add(pdDTO);
        }
        return listaPdDTO;
    }
}
