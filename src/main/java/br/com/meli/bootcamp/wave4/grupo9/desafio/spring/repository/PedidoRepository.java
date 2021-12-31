package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/***
 * @author Fernando Netto
 */
@Repository
public class PedidoRepository {
    List<Pedido> listaPedido = new ArrayList<>();

    public void salvarPedido(Pedido pedido) {
        listaPedido.add(pedido);
    }

    public List<Pedido> listarPedido(){
        return listaPedido;
    }

    private Long getMaxId(){
        Long id = 0L;
        for ( Pedido p : listaPedido ) {
            if (p.getId() != null && p.getId().compareTo(id) > 0 ){
                id = p.getId();
            }
        }
        return id;
    }

    public Pedido criaPedido(List<ItemCarrinho> listItemCarrinho, long idCliente, String enderecoEntrega) {
        Long idPedido = getMaxId()+1L;
        BigDecimal valorPedido = BigDecimal.ZERO;
        BigDecimal valorFrete = BigDecimal.ZERO;
        Pedido p = new Pedido( idPedido, idCliente, listItemCarrinho, valorFrete, valorPedido);
        p.calculaValorTotalPedido(enderecoEntrega);
        salvarPedido(p);
        return p;
    }

}
