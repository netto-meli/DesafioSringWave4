package br.com.meli.bootcamp.wave4.grupo9.desafio.spring.service;

import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.entity.*;
import br.com.meli.bootcamp.wave4.grupo9.desafio.spring.exception.ErrorProcesamentoException;
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
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Pedido> encontrarTodos() throws ErrorProcesamentoException {
        return pedidoRepository.listagem();
    }

    public Pedido encontrarPorId(long id) throws ErrorProcesamentoException{
        Optional<Pedido> pedido = pedidoRepository.listagem().stream().filter(x-> x.getId() == id).findFirst();
        return pedido.orElse(null);
    }

    public List<Pedido> ordernarLista(Integer number) throws ErrorProcesamentoException{
        List<Pedido> lista = new ArrayList<>();

        if (number == 0) {
            lista = pedidoRepository.listagem().stream().sorted((p1, p2) -> sortCliente(p1,p2) ).collect(Collectors.toList());
        }
        else if(number == 1) {
            lista = pedidoRepository.listagem().stream().sorted((p2, p1) -> sortCliente(p2,p1)).collect(Collectors.toList());
        }
        else if(number == 2) {
            lista = pedidoRepository.listagem().stream().sorted((p1, p2) -> p1.getValorTotal().compareTo(p2.getValorTotal())).collect(Collectors.toList());
        }
        else if(number == 3) {
            lista = pedidoRepository.listagem().stream().sorted((p2, p1) -> p2.getValorTotal().compareTo(p1.getValorTotal())).collect(Collectors.toList());
        }

        return lista;
    }

    private int sortCliente(Pedido p1, Pedido p2){
        Cliente cli1 = clienteRepository.get(p1.getIdCliente());
        Cliente cli2 = clienteRepository.get(p2.getIdCliente());
        return cli1.getNome().compareTo(cli2.getNome());
    }
}
