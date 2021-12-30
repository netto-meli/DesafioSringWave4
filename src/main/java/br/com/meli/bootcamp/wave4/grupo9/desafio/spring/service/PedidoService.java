package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.*;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.ClienteRepository;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> encontrarTodos(){
        return pedidoRepository.listarPedido();
    }

    public Pedido encontrarPorId(long id){
        Optional<Pedido> pedido = pedidoRepository.listarPedido().stream().filter(x-> x.getId() == id).findFirst();
        return pedido.orElse(null);
    }

    public List<Pedido> ordernarLista(Integer number) {
        List<Pedido> lista = new ArrayList<>();

        if (number == 0) {
            lista = pedidoRepository.listarPedido().stream().sorted((p1, p2) -> p1.getId().compareTo(p2.getId()) ).collect(Collectors.toList());
        }
        else if(number == 1) {
            lista = pedidoRepository.listarPedido().stream().sorted((p1, p2) -> p2.getId().compareTo(p1.getId())).collect(Collectors.toList());
        }
        else if(number == 2) {
            lista = pedidoRepository.listarPedido().stream().sorted((p1, p2) -> p1.getValorTotal().compareTo(p2.getValorTotal())).collect(Collectors.toList());
        }
        else if(number == 3) {
            lista = pedidoRepository.listarPedido().stream().sorted((p1, p2) -> p2.getValorTotal().compareTo(p1.getValorTotal())).collect(Collectors.toList());
        }

        return lista;
    }
}
