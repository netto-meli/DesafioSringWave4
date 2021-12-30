package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.ItemCarrinho;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import org.springframework.stereotype.Repository;

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

    public Pedido criaPedido(List<ItemCarrinho> listItemCarrinho) {
        return null;
    }

}
