package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Pedido;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepository {

    List<Pedido> listaPedido = new ArrayList<>();

    public void salvarPedido(Pedido pedido) {
        listaPedido.add(pedido);
    }

    public List<Pedido> listarPedido(){
        return listaPedido;
    }

}
